drop table if exists users;
create table users
(
    id         int AUTO_INCREMENT,
    name       varchar(32)        not null,
    username   varchar(20) unique not null,
    password   varchar(300)        not null,
    sex        varchar(2) default '未知'
        checK (sex in ('男', '女', '未知')),
    address    varchar(64)        not null,
    education  varchar(32)        not null,
    phone      varchar (11)        not null,
    login_time date,
    primary Key (id)
)engine=Innodb AUTO_INCREMENT=1000000,comment '用户表';

create table company
(
    id         int AUTO_INCREMENT,
    name       varchar(32)        not null,
    username   varchar(20) unique not null,
    password   varchar(20)        not null,
    sex        varchar(2) default '未知' check (sex in ('男', '女', '未知')),
    phone      varchar(11)        not null,
    login_time date,
    primary key (id)
) engine=Innodb AUTO_INCREMENT=2000000 comment '企业表';


drop table if exists Job_table;
create table Job_table
(
    id          int AUTO_INCREMENT,
    company     varchar(64) not null,
    salary      varchar(32) not null,
    description LONGTEXT    not null,
    title       varchar(32) not null,
    hr_id       int comment '发布者id' not null,
    create_time date,
    update_time date,
    salary_id   int,
    primary Key (id)
)engine=Innodb AUTO_INCREMENT=3000000,comment '招聘信息表';


drop table if exists Resume;
create table Resume
(
    id          int AUTO_INCREMENT,
    Uid         int          not null,
    name        varchar(20)  not null,
    address     varchar(32) null,
    education   varchar(32)  not null,
    ex_title    varchar(32)  not null,
    ex_salary   varchar(32) null,
    description longtext     not null,
    photo       varchar(255) not null,
    status      int default '0'
        check (status in (0, 1)) comment '1发布，0草稿',
    Did         int null comment '选择模板id',
    primary Key (id)
) engine=Innodb AUTO_INCREMENT=4000000,comment '简历表';


drop table if exists Admin;
create table Admin
(
    Aid      int primary Key,
    username varchar(32),
    password varchar(32)
);

insert into Admin (Aid, username, password)
values ('1', 'admin', 'admin');


drop table if exists Favor;
create table Favor
(
    Uid    int not null,
    Tid    int not null,
    orders int primary Key
);


drop table if exists address_table;
create table address_table
(
    address varchar(32) not null,
    addr_id int PRIMARY Key
);

--
drop table if exists job_category;
create table job_category
(
    title varchar(32) not null,
    Tid   int  auto_increment PRIMARY KEY
)auto_increment=1;

--
drop table if exists salary_table;
create table salary_table
(
    category  varchar(20) not null,
    salary_id int PRIMARY Key
);
insert
salary_table (category,salary_id)
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

CREATE TABLE menu_table
(
    menu_id   int  NOT NULL AUTO_INCREMENT,
    component varchar(100) DEFAULT NULL,
    path      varchar(100) DEFAULT NULL,
    redirect  varchar(100) DEFAULT NULL,
    name      varchar(100) DEFAULT NULL,
    title     varchar(100) DEFAULT NULL,
    icon      varchar(100) DEFAULT NULL,
    parent_id int  DEFAULT NULL,
    is_leaf   varchar(1)   DEFAULT NULL,
    hidden    tinyint(1) DEFAULT NULL,
    PRIMARY KEY (menu_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 ;



CREATE TABLE user_role
(
    id      int NOT NULL AUTO_INCREMENT,
    user_id int  DEFAULT NULL,
    role_id int  DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=5 ;


CREATE TABLE role_menu
(
    id      int  NOT NULL AUTO_INCREMENT,
    role_id int  DEFAULT NULL,
    menu_id int  DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=5 ;













