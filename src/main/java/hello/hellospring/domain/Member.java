package hello.hellospring.domain;

public class Member {

    //필드 2개, private 은 객체 내부에서만 접근 가능
    private Long id;//데이터 구분용 id
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
