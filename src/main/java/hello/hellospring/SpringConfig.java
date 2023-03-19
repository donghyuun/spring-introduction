package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;


//직접 자바 코드로 동록
@Configuration //스프링이 뜰때 @Configuaration 을 읽고 @Bean에 있는 로직을 호출하여 스프링빈에 등록한다.
public class SpringConfig {
    private final MemberRepository memberRepository;//스프링데이터jpa가 알아서 만들어놓은 구현체가 등록된다.

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
     }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);//injection 받은걸 의존관계로 넣어준다.
    }

    /*@Bean
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);

        //dataSource는 DB와 관계된 커넥션 정보를 담고있으며, 사용하기 위해 설정한 property file을 통해 DataSource를 빈으로 등록한다.
        //return new JdbcTemplateMemberRepository(dataSource);

        return new JpaMemberRepository(em);
    }*/

}
