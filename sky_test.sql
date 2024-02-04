
drop table if exists users;
create table users(
id  bigint  AUTO_INCREMENT,
name varchar(32) not null ,
username varchar(20) unique not null,
password varchar(20) not null,
sex varchar(2)  default '未知'
		checK(sex in('男','女','未知')),
address varchar(64) not null,
education varchar(32) not null,
phone varchar(11) not null,
primary Key(id)
)engine=Innodb AUTO_INCREMENT=1000000,comment '用户表';


drop table if exists company;
create table company(
id  bigint  AUTO_INCREMENT,
name varchar(32) not null ,
username varchar(20) unique not null,
password varchar(20) not null,
sex varchar(2)  default '未知'
		check(sex in('男','女','未知')),
phone varchar(11) not null,
primary key(id)
)engine=Innodb AUTO_INCREMENT=2000000,comment '企业表';


drop table if exists Job_table ;
create table Job_table(
id bigint AUTO_INCREMENT,
company varchar(64) not null,
salary varchar(32) not null,
description LONGTEXT not null,
title varchar(32) not null,
hr_id bigint comment '发布者id' not null,
primary Key(id)
)engine=Innodb AUTO_INCREMENT=3000000,comment '招聘信息表';


drop table  if exists Resume;
create table Resume(
id bigint AUTO_INCREMENT,
Uid bigint not null,
name varchar(20) not null,
address varchar(32) null,
education varchar(32) not null,
ex_title varchar(32) not null,
ex_salary varchar(32) null,
description longtext not null,
photo varchar(255) not null,
status int default '0' 
	check(status in (0,1)) comment '1发布，0草稿',
Did bigint null comment '选择模板id',
primary Key (id)
) engine=Innodb AUTO_INCREMENT=4000000,comment '简历表';


drop table if exists Admin;
create table Admin(
Aid bigint primary Key,
username varchar(32),
password varchar(32)
);

insert into Admin (Aid, username, password) values('1', 'admin', 'admin');


drop table if exists Favor;
create table Favor(
Uid bigint not null,
Tid bigint not null,
orders bigint primary Key 
);


drop table if exists address_table ;
create table address_table(
address varchar(32) not null,
addr_id bigint PRIMARY Key
);

drop table if exists job_category;
create table job_category(
title varchar(32) not null,
Tid bigint PRIMARY KEY
);

drop table if exists salary_table;
create table salary_table(
salary varchar(20) not null,
salary_id bigint PRIMARY Key
);
insert salary_table (salary,salary_id)
VALUES
('不限','1'),
('2K以下','2'),
('2K-5','3'),
('5K-10K','4'),
('10K-15K','5'),
('15K-25K','6'),
('25K-50K','7'),
('50K以上','8'),
('面议','9');




















