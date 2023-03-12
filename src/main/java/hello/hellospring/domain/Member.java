package hello.hellospring.domain;

import org.springframework.boot.autoconfigure.quartz.QuartzTransactionManager;

import javax.persistence.*;

//jpa는 자바의 표준 인터페이스이고, 구현은 여러업체가 하는데 여기선 hibernate를 사용
//객체랑 ORM(Object와 Relational database를 Mapping) 기술, @로 DB와 Mapping 한다!!!!!!!!!!!!!!.
@Entity//jpa가 관리하는 Entity라는 뜻
public class Member {

    //필드 2개, private 은 객체 내부에서만 접근 가능
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)//쿼리에 값을 넣는게 아니라 db가 알아서 생성해주는걸 IDENTITY 전략이라고 함
    private Long id;//데이터 구분용 id

    @Column(name = "name")//Column명이 "name"인 경우
    private String name;

    //메서드 3개
    public Long getId() {//getter
        return id;//반환타입 Long
    }

    public void setId(Long id) {
        this.id = id;
    }//setter

    public String getName() {
        return name;//반환타입 String
    }

    public void setName(String name) {
        this.name = name;
    }
}
