package com.example.examplespring.controller;

import com.example.examplespring.domain.Member;
import com.example.examplespring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller //스프링이 어노테이션을 보고 콘트롤러를 확인한다. 스프링이 실행시 콘테이너를 찾는다.
public class MemberController {

    //멤버서비스를 가져다 쓰기
    private final MemberService memberService;

    //스프링컨테이너에 등록하여 사용하기
    //생성자로 한다.

//    @Autowired    //setter 주입
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

    @Autowired //이게 있으면 스프링이 콘테이너에서 멤버서비스를 가져온다.
    public MemberController(MemberService memberService) {          //DI의 생성자 주입
        this.memberService = memberService;
    }

    @GetMapping("members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping(value = "members/new")
    public String create(memberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
