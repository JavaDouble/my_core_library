USE test;
DROP TABLE IF EXISTS userinfo_lqs;
CREATE TABLE userinfo_lqs(
id INT(5) PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(30),
PASSWORD VARCHAR(30),
email VARCHAR(30),
nickname VARCHAR(30),
account DOUBLE(10,2)
);

DESC userinfo_lqs;
--  第一种插入自增字段的数据方式 id null
INSERT INTO userinfo_lqs(id,username,PASSWORD,email,nickname,account)
VALUES(NULL,'张三','123456','123456@admin.com','校长',8888);
INSERT INTO userinfo_lqs(id,username,PASSWORD,email,nickname,account)
VALUES(NULL,'李四','123456','123456@admin.com','主任',8888);
INSERT INTO userinfo_lqs(id,username,PASSWORD,email,nickname,account)
VALUES(NULL,'王五','123456','123456@admin.com','教师',8888);
INSERT INTO userinfo_lqs(id,username,PASSWORD,email,nickname,account)
VALUES(NULL,'rose','123456','123456@admin.com','副校长',8888);
INSERT INTO userinfo_lqs(id,username,PASSWORD,email,nickname,account)
VALUES(NULL,'lily','123456','123456@admin.com','主任',8888);
INSERT INTO userinfo_lqs(id,username,PASSWORD,email,nickname,account)
VALUES(NULL,'tom','123456','123456@admin.com','教师',8888);
COMMIT;
INSERT INTO userinfo_lqs(id,username,PASSWORD,email,nickname,account)
VALUES(NULL,'boby','123456','123456@admin.com','教师',8888);
-- mysql中的DML语句是自动提交的

SELECT * FROM userinfo_lqs;

USE test;
DROP TABLE IF EXISTS userinfo_lqs;
CREATE TABLE userinfo_lqs(
id INT(5) PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(30),
PASSWORD VARCHAR(30),
email VARCHAR(30),
nickname VARCHAR(30),
account DOUBLE(10,2)
);

DESC userinfo_lqs;
--  第二种插入自增字段的数据方式 id 0
INSERT INTO userinfo_lqs(id,username,PASSWORD,email,nickname,account)
VALUES(0,'张三','123456','123456@admin.com','校长',8888);
INSERT INTO userinfo_lqs(id,username,PASSWORD,email,nickname,account)
VALUES(0,'李四','123456','123456@admin.com','主任',8888);
INSERT INTO userinfo_lqs(id,username,PASSWORD,email,nickname,account)
VALUES(0,'王五','123456','123456@admin.com','教师',8888);
INSERT INTO userinfo_lqs(id,username,PASSWORD,email,nickname,account)
VALUES(0,'rose','123456','123456@admin.com','副校长',8888);
INSERT INTO userinfo_lqs(id,username,PASSWORD,email,nickname,account)
VALUES(0,'lily','123456','123456@admin.com','主任',8888);
INSERT INTO userinfo_lqs(id,username,PASSWORD,email,nickname,account)
VALUES(0,'tom','123456','123456@admin.com','教师',8888);
COMMIT;
INSERT INTO userinfo_lqs(id,username,PASSWORD,email,nickname,account)
VALUES(0,'boby','123456','123456@admin.com','教师',8888);
-- mysql中的DML语句是自动提交的

SELECT * FROM userinfo_lqs;


USE test;
DROP TABLE IF EXISTS userinfo_lqs;
CREATE TABLE userinfo_lqs(
id INT(5) PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(30),
PASSWORD VARCHAR(30),
email VARCHAR(30),
nickname VARCHAR(30),
account DOUBLE(10,2)
);

DESC userinfo_lqs;
--  第三种插入自增字段的数据方式 不插入该字段
INSERT INTO userinfo_lqs(username,PASSWORD,email,nickname,account)
VALUES('张三','123456','123456@admin.com','校长',8888);
INSERT INTO userinfo_lqs(username,PASSWORD,email,nickname,account)
VALUES('李四','123456','123456@admin.com','主任',8888);
INSERT INTO userinfo_lqs(username,PASSWORD,email,nickname,account)
VALUES('王五','123456','123456@admin.com','教师',8888);
INSERT INTO userinfo_lqs(username,PASSWORD,email,nickname,account)
VALUES('rose','123456','123456@admin.com','副校长',8888);
INSERT INTO userinfo_lqs(username,PASSWORD,email,nickname,account)
VALUES('lily','123456','123456@admin.com','主任',8888);
INSERT INTO userinfo_lqs(username,PASSWORD,email,nickname,account)
VALUES('tom','123456','123456@admin.com','教师',8888);
COMMIT;
INSERT INTO userinfo_lqs(username,PASSWORD,email,nickname,account)
VALUES('boby','123456','123456@admin.com','教师',8888);
-- mysql中的DML语句是自动提交的

SELECT * FROM userinfo_lqs;

-- 查询事物状态  查看是否开启了自动提交，如果为1，表明开启了。如果是0，表明关闭。      
-- 并关闭mysql事物自动提交
select @@autocommit;
set autocommit=0;
set global init_connect="set autocommit=0";