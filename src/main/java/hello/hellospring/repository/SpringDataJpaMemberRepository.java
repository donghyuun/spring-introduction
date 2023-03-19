package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//인터페이스만 있지만 springdatajpa가 인터페이스에 대한 구현체를 자동으로 만들어서 구현체를 스프링빈에 등록해준다.
// => 인터페이스를 통한 기본적인 CRUD 기능을 제공한다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    //Member 의 id 가 Long 타입임

    //아래와 같이 쓰면 findBy~ 패턴이 springdatajpa에 등록되어 있어서
    //JPQL select m from Member m where m.name = ? 로 쿼리를 알아서 짜준다.
    @Override
    Optional<Member> findByName(String name);
}
