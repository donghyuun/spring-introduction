package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import javax.xml.crypto.Data;


//직접 자바 코드로 동록
@Configuration //스프링이 뜰때 @Configuaration 을 읽고 @Bean에 있는 로직을 호출하여 스프링빈에 등록한다.
public class SpringConfig {

    private DataSource dataSource;//application.properties 에서 db에

    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);

        //dataSource는 DB와 관계된 커넥션 정보를 담고있으며, 사용하기 위해 설정한 property file을 통해 DataSource를 빈으로 등록한다.
        return new JdbcTemplateMemberRepository(dataSource);
    }

}
