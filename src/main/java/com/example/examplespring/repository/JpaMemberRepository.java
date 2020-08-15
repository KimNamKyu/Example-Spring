package com.example.examplespring.repository;

import com.example.examplespring.domain.Member;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

//JPA
@Transactional
public class JpaMemberRepository implements MemberRepository{

    //Jpa를 사용하기위해 EntityManager를 주입받아야한다.
    private final EntityManager em;
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        //객체지향 sql를 사용한다.
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //Entity를 대상으로 조회 한다. 멤버 객체 자체를 select 한다.
        return em.createQuery("select m from Member m", Member.class).getResultList();

    }
}
