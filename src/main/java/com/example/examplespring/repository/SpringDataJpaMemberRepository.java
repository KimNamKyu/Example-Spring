package com.example.examplespring.repository;

import com.example.examplespring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    /*
    * Spring Data JPA
    * 인터페이스를 통한 기본적인 CRUD제공
    * findByName() / findByEmail 처럼 메서드 이름만으로 조회 기능 제공
    * 페이징 기능 자동 제공
    * 동적쿼리는 Querydsl이라는 라이브러리를 사용하여 자바코드를 안전하게 작성, 동적쿼리 편리하게 작성
    * */
    //JPQL select m from Member m where m.name = ? 이렇게 작성해준다. 단순한것은 Interface 이름 만으로 해결해준다.
    @Override
    Optional<Member> findByName(String name);
}
