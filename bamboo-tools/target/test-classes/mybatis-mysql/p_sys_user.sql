use sea;
create table p_sys_user(  
    staff_no varchar(16) primary key,  
    name varchar(20),
    pwd varchar(50),
    org_no varchar(16),
    create_date datetime
); 

insert into p_sys_user(staff_no, name, pwd, org_no, create_date) values('wxz', '王小竹', "123456", '13103', sysdate());
insert into p_sys_user(staff_no, name, pwd, org_no, create_date) values('wangwu', '王五', "123456", '13103', sysdate());
insert into p_sys_user(staff_no, name, pwd, org_no, create_date) values('zhangsan', '张三', "123456", '13103', sysdate());

select * from p_sys_user;

-- drop table p_sys_user;