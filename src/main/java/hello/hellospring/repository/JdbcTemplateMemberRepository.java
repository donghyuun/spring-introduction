package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository {

    //필드
    private final JdbcTemplate jdbcTemplate;

    @Autowired//생성자가 하나인 경우 @Autowired 생략가능!
    public JdbcTemplateMemberRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);//DataSource 를 injection 받음 => spring jdbc 을 이용해 db 접근가능
    }

    //추상메서드 오버라이딩(overriding)
    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");//테이블명과 pk명

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ? ", memberRowMapper(), id);//결과를 memberRowMapper 로 매핑하고, List 형태로 반환
        // '?' 부분에 매개변수 id 가 들어가는 듯
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        //쿼리날리고 결과를 memberRowMapper 로 매핑하고, List 형태로 반환, '?' 부분에 매개변수 id 가 들어가는 듯
        List<Member> result = jdbcTemplate.query("select * from member where name = ? ", memberRowMapper(), name);

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());//쿼리의 결과가 memberRowMapper()의 인자로 들어간 후 memberRowMapper()
        //함수가 리턴하는값을 findAll()에서 리스트 형태로 리턴하는 구조
    }

    private RowMapper<Member> memberRowMapper(){
        return new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {//객체 생성에 관함

                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return member;
            }
        };
    }
}


//템플릿메서드 패턴과 콜백함수를 이용한 양상