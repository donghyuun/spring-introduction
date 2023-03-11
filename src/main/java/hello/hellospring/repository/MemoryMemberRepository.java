package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository {//MemberRepository의 구현 클래스

    private static Map<Long, Member> store = new HashMap<>();//Map 컬렉션의 종류인 HashMap 컬렉션 생성, 이름을 store 라고 지정
    private static long sequence = 0L; //0,1,2...처럼 key값을 생성해 주는 기능

    //추상 메서드 재정의
    @Override
    public Member save(Member member) {//매개변수로 Member 객체를 받음, name은 들어있는 상태
        member.setId(++sequence);//id값 세팅
        store.put(member.getId(), member); //store에 저장, key는 id, value는 member 객체
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));//해당 id를 가지는 객체 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))//람다식 사용, 파라미터로 넘어온 name과 같은지 확인
                .findAny();//findAny는 "하나라도 찾으면" 이라는 의미
    }

    @Override
    public List<Member> findAll() {//List로 반환함, 더 편함
        return new ArrayList<>(store.values());//store.values() 는 member 들을 의미
    }

    public void clearStore(){//일반 메서드O, 재정의 메서드X
        store.clear();//store를 싹 비움
    }
}
