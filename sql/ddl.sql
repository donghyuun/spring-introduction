//여기 적어놔서 db에 관한 정보를 파악할 수 있음
drop table if exists member CASCASDE;
create table member
(
    id  bigint generated by default as identity,
    name varchar(255),
    primary key(id)
)