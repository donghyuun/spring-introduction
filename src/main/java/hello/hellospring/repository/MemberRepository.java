package hello.hellospring.repository;


import hello.hellospring.domain.Member;//다른 패키지의 클래스는 import 해와야 함

import java.util.List;
import java.util.Optional;

//인터페이스 생성
public interface MemberRepository {
    Member save(Member member);//"추상 메서드", 회원을 저장소에 저장. 회원을 저장하면 저장된 회원이 반환된다.

    Optional<Member> findById(Long id);//저장소에서 회원을 id 로 찾음
    Optional<Member> findByName(String name);//저장소에서 회원을 name 으로 찾음
    List<Member> findAll();//지금까지 저장소에 저장된 모든 회원리스트를 반환

}

//"Optional"은 가져온게 null일때 null을 바로 반환하지 말고 optional 이란걸로 감싸서 반환. java8의 기능