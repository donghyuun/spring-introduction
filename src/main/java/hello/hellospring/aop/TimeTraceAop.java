package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {//얘를 @Component를 이용해 스프링빈에 등록해준다.

    @Around("execution(* hello.hellospring..*(..))") //적용시킬 대상을 타겟팅한다!!, 현재는 hello.hellospring 하위에 모두 적용
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{ //중간에 호출될때마다 intercept해서 딱딱 처리해준다.
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            Object result = joinPoint.proceed();//다음 메서드로 진행이 된다.
            return result;
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }

    }
}
