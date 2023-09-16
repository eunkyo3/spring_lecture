select * from member;
Drop table member;
CREATE TABLE member
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

insert into member(name) values('정은교');
insert into member(name) values('강해린');