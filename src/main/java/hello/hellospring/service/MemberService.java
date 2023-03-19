package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//비즈니스 로직
//@Service//스프링이 올라올때 스프링 컨테이너에 자동으로 등록됨(@Controller, @Service, @Repository 모두 해당)
@Transactional//jpa를 쓰려면 항상 @Transactional 이 있어야 한다.
public class MemberService {//MemberService가 스프링빈에 등록되어 있지 않으면 Autowired를 적용할 수 없음

    private final MemberRepository memberRepository;//인터페이스

    //@Autowired//MemberService를 생성할때 스프링 컨테이너에서 MemberRepository를 딱 넣어준다.
    public MemberService(MemberRepository memberRepository){//인터페이스에 외부에서 구현 클래스(repository를 받아와 대입, Dependency Ejection
        this.memberRepository = memberRepository;
    }

    /* 회원 가입 */
    public Long join(Member member){
        //같은 이름이 있는 중복 회원 존재하는지 검증
        validateDuplicateMember(member);
        //통과 시 저장
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /* 전체 회원 조회 */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
