package com.example.examplespring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    /*
    * MVC 방식으로 데이터 랜더링
    * */
    @GetMapping("hello") // GetMapplng == Get 방식을 의미!
    public String hello(Model model){
        model.addAttribute("data", "hello!!");   //키 : data & value : hello
        return "hello";     //resource에있는 hello 를 찾아 redering을 해라!
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-templete";
    }

    /*
    * API방식으로
    * Spring 동작방식
    * Controller >> HttpMessageConveter에서 단순문자일경우 stringConverter가 동작 객체일 경우 JsonConverter가 동작
    * */
    @GetMapping("hello-string")
    @ResponseBody // http에서 body에 이 데이터를 직접 넣겟다.
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody // http에서 body에 이 데이터를 직접 넣겟다.
    public Hello helloAPI(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    //property 프로퍼티 방식 오랜만이야
    static class Hello {
        private String name;

        public  String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
