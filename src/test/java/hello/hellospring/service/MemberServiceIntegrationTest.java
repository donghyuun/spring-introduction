package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional  //시작전에 transaction 걸고, test 후 db를 다시 rollback 해준다(데이터 반영X => 다음 test에 영향X)
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository; //@Configuration 한거에서 올라올거임(JdbcMemoryMember 구현 객체)

    @Test
    void 회원가입() {
        //given(뭐가 주어졌을때)
        Member member = new Member();
        member.setName("spring");

        //when(뭐를 하면)
        Long saveId = memberService.join(member);//가입 시 멤버 아이디 반환

        //then(뭐가 되어야 한다.)
        Member findMember = memberService.findOne(saveId).get();
        org.assertj.core.api.Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        //memberService.join(member2) 문을 실행했을때 IllegalStateException 과 동일한 예외타입이 터지는 지 확인, 터지면 성공, 안터지면 실패
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");//asertThrow 는 실패시 메시지를 반환

        //then
    }

}