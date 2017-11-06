use sea;
create table p_user(  
    id int primary key auto_increment,  
    name varchar(10),
    sex char(2)
); 

insert into p_user(name,sex) values('A',"男");  
insert into p_user(name,sex) values('B',"女");  
insert into p_user(name,sex) values('C',"男");  
