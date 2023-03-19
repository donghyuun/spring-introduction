package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

//jpa를 쓰려면 항상 transcation이 있어야 한다.class
@Transactional
public class JpaMemberRepository implements MemberRepository{

    //jpa는 EntityManager로 모든게 동작. 라이브러리를 받았기 때문에 spring boot가 자동으로 생성해줬기 때문에 이걸 injection 해준다.
    private final EntityManager em;//내부적으로 dataSource를 들고있어 db와 통신하는걸 내부에서 다 처리한다.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);//자동으로 내부적으로 쿼리써서 "저장"해줌, setId도 해줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);//조회할 타입 및 식별자 pk
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {//pk기반이 아닌 나머지들은 jpql 이라는 객체지향 쿼리를 써야함, sql 랑 거의 비슷
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)//domain의 Member 클래스의 column 명을 따라간다.
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {//jpql 사용
        //보통은 테이블을 대상으로 쿼리를 날리는데 이건 객체(Member Entity)를 대상으로 쿼리를 날림
        List<Member> result = em.createQuery("select m from Member as m", Member.class)
                .getResultList();

        return result;
    }
}
