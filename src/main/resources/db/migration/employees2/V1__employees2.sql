create table employees(
id bigint auto_increment,
emp_name varchar(255),
constraint employees primary key(id));

insert into employees(emp_name) values('John Doe');
insert into employees(emp_name) values('Jane Doe');
insert into employees(emp_name) values('Jimmy Doe');
insert into employees(emp_name) values('Bob Doe');