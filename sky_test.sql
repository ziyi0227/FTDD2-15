drop table if exists users;
create table users
(
    id         varchar(52),
    name       varchar(32),
    username   varchar(20) unique not null,
    password   varchar(300)        not null,
    sex        tinyint default 3
        checK (sex in (1, 2, 3)) comment ' 1男 2女 3隐私',
    live_city    varchar(64),
   login_time date,
   type tinyint default 1 check (type in (1, 2,3)) comment '1用户 2企业 3管理',
    primary Key (id)
)engine=Innodb ,comment '用户表';

-- create table company
-- (
--     id         int AUTO_INCREMENT,
--     name       varchar(32)        not null,
--     username   varchar(20) unique not null,
--     password   varchar(20)        not null,
--     sex        varchar(2) default '未知' check (sex in ('男', '女', '未知')),
--     phone      varchar(11)        not null,
--     login_time date,
--     primary key (id)
-- ) engine=Innodb AUTO_INCREMENT=2000000 comment '企业表';


drop table if exists Job_table;
create table Job_table
(
    id          int AUTO_INCREMENT,
    jd_no varchar(52) not null  comment '职位代码',
    jd_title varchar(52) not null  comment '职位名称',
    company     varchar(64) comment '公司名称',
    city varchar(32) comment '工作城市',
    jd_sub_type varchar(32) comment '职位类型',
    require_numss bigint comment '招聘人数',
    min_salary  int comment '最低薪资',
    max_salary  int comment '最高薪资',
    start_date  date comment '开始时间',
    end_date    date comment '结束时间',
    is_travel  tinyint default 0 check (is_travel in (0, 1)) comment '是否出差,0不出差，1出差',
    min_years   varchar(10) comment '最低工作年限',
    min_education varchar(32) comment '最低学历',
    title_skill varchar(32) comment '技能要求',
    knowledge varchar(32) comment '知识要求',
    quality varchar(32) comment '素质要求',
    primary Key (id)
)engine=Innodb comment '招聘信息表';
--  --

drop table if exists action_table;
create table action_table
(
    user_id int not null,
    job_id int not null ,
    browsed varchar(1) default ('0') check ( browsed in('1','0') ),
    delivered varchar(1) default ('0') check ( delivered in('1','0') ),
    satisfied varchar(1) default ('0') check ( satisfied in('1','0') )
);



drop table if exists Resume;
create table Resume
(
    id          int ,
    user_id     int not null,
    name        varchar(32) not null,
    phone       varchar(11) not null,
    age         varchar(3) not null,
    live_city   varchar(32) not null,
    degree      varchar(32) not null,
    desire_jd_type varchar(32) not null,
    desire_jd_salary_id varchar(32) not null,
    desire_jd_industry varchar(32) not null,
    desire_city varchar(32) not null,
    experience  varchar(1000) not null,
    start_work_date Year not null,
    current_salary_id varchar(32) not null,
    cur_industry varchar(32) not null,
    cur_jd_type varchar(32) not null

) engine=Innodb comment '简历表';

drop table if exists user_exposure;
create table user_exposure
(
    user_id varchar(52) not null,
    jd_no   varchar(52) not null
);

-- drop table if exists Admin;
-- create table Admin
-- (
--    Aid      int primary Key,
--    username varchar(32),
--    password varchar(32)
-- );

-- insert into Admin (Aid, username, password)
-- values ('1', 'admin', 'admin');


drop table if exists Favor;
create table Favor
(
   user_id varchar (52) not null,
   jd_no varchar(52) not null,
   id int primary Key
)comment '收藏表';


-- drop table if exists address_table;
-- create table address_table
-- (
--     address varchar(32) not null,
--     addr_id int PRIMARY Key
-- );

-- 此处再执行
-- drop table if exists job_category;
-- create table job_category
-- (
--     title varchar(32) not null,
--     Tid   int  auto_increment PRIMARY KEY
-- )auto_increment=1;

--
drop table if exists salary_table;
create table salary_table
(
    category  varchar(20) not null,
    salary_id varchar(20) not null
);
insert
salary_table (category,salary_id)
VALUES
    ('0000000000','面议'),
    ('0000001000','1000元以下'),
    ('0100002000','1000-2000元/月'),
    ('0200104000','2001-4000元/月'),
    ('0400106000','4001-6000元/月'),
    ('0600108000','6001-8000元/月'),
    ('0800110000','8001-10000元/月'),
    ('100001150000','100000元以上'),
    ('1000115000','10001-15000元/月'),
    ('1500120000','15000-20000元/月'),
    ('1500125000','15000-25000元/月'),
    ('2000130000','20000-30000元/月'),
    ('2500199999','25000元/月以上'),
    ('3000150000','30000-50000元/月'),
    ('3500150000','35000-50000元/月'),
    ('5000170000','50000-70000元/月'),
    ('70001100000','70000-100000元/月'),
    ('2500135000','25000-35000元/月');

drop table if exists menu_table;
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


drop table if exists user_role;
CREATE TABLE user_role
(
    id      int NOT NULL AUTO_INCREMENT,
    user_id varchar(52) DEFAULT NULL,
    role_id int DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=5 ;

drop table if exists role_menu;
CREATE TABLE role_menu
(
    id      int  NOT NULL AUTO_INCREMENT,
    role_id int  DEFAULT NULL,
    menu_id int  DEFAULT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=5;

-- create table action_table
-- (
--     user_id int not null,
--     job_id int not null ,
--     browsed varchar(1) default ('0') check ( browsed in('1','0') ),
--     delivered varchar(1) default ('0') check ( delivered in('1','0') ),
--     satisfied varchar(1) default ('0') check ( satisfied in('1','0') )
-- )











