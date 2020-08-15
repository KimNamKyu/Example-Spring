package com.example.examplespring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {
    //중간에 인터셉터 해서 사용도 가능
    //변경사항 있을 시 이 로직만 변경하여 사용가능
    //원하는 적용 대상을 선택할 수 있다.
    /**
     * AOP적용 후 의존관계
     * controller 에서 프록시Service를 통해
     */
    @Around("execution(* com.example.examplespring..*(..))")
    public Object execut(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }

    }
}
