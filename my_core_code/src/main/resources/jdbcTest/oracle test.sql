-- JDBC所用数据库
create table userinfo_lqs(
id number(5),
username VARCHAR2(30),
password VARCHAR2(30),
email VARCHAR2(30),
nickname VARCHAR(30),
account NUMBER(10,2)
);

create sequence seq_userinfo_id_lqs START WITH 100 INCREMENT BY 10;

desc userinfo_lqs;
insert into userinfo_lqs(id,username,password,email,nickname,account)
values(seq_userinfo_id_lqs.NEXTVAL,'张三','123456','123456@admin.com','校长',8888);
insert into userinfo_lqs(id,username,password,email,nickname,account)
values(seq_userinfo_id_lqs.NEXTVAL,'李四','123456','123456@admin.com','主任',8888);
insert into userinfo_lqs(id,username,password,email,nickname,account)
values(seq_userinfo_id_lqs.NEXTVAL,'王五','123456','123456@admin.com','教师',8888);
insert into userinfo_lqs(id,username,password,email,nickname,account)
values(seq_userinfo_id_lqs.NEXTVAL,'rose','123456','123456@admin.com','副校长',8888);
insert into userinfo_lqs(id,username,password,email,nickname,account)
values(seq_userinfo_id_lqs.NEXTVAL,'lily','123456','123456@admin.com','主任',8888);
insert into userinfo_lqs(id,username,password,email,nickname,account)
values(seq_userinfo_id_lqs.NEXTVAL,'tom','123456','123456@admin.com','教师',8888);
commit;

select * from userinfo_lqs;


-- javaee包下listEmpServlet所用数据库文件
-- DROP SEQUENCE emp_id_seq;
CREATE SEQUENCE emp_id_seq START WITH 1 INCREMENT BY 1;
-- DROP TABLE t_emp_lqs;
CREATE TABLE t_emp_lqs(
id NUMBER(4) PRIMARY KEY,
username VARCHAR2(20),
salary NUMBER(7,2),
age NUMBER(3)
);

INSERT INTO t_emp_lqs VALUES (emp_id_seq.NEXTVAL,'Lisa',3000.00,20);
INSERT INTO t_emp_lqs VALUES (emp_id_seq.NEXTVAL,'花儿',3000.00,20);
INSERT INTO t_emp_lqs VALUES (emp_id_seq.NEXTVAL,'少年',3000.00,20);
COMMIT;
SELECT * FROM t_emp_lqs;