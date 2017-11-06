use sea;
create table o_org(  
    org_no varchar(16) primary key,  
    org_name varchar(20),
    org_type varchar(2)
); 

insert into o_org(org_no, org_name, org_type) values('13103', '江苏省', "02");
insert into o_org(org_no, org_name, org_type) values('1310301', '南京市', "03");

select * from o_org;