use sea;
create table p_access_org(  
    staff_no varchar(16),  
    org_no varchar(16),
    org_type char(2)
); 

insert into p_access_org(staff_no, org_no, org_type) values('wxz', '13103', "02");
insert into p_access_org(staff_no, org_no, org_type) values('wxz', '1310301', "03");

select * from p_access_org;
