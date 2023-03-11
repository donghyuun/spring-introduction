package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){//테스트를 실행할 때마다 독립적으로 시행
        //저장소(DB) 공유하게 됨
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
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
    @Test
    void finalMember() {
    }

    @Test
    void findOne() {
    }
}