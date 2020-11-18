drop table if exists Dept;
drop table if exists Emp;

create table Dept(  
  deptNo     number(2,0),  
  dName      varchar2(14),  
  loc        varchar2(13),  
  constraint pkDept primary key (deptNo)  
);

create table emp(  
  empNo    number(4,0),  
  eName    varchar2(10),  
  job      varchar2(9),  
  mgr      number(4,0),  
  hireDate date,  
  sal      number(7,2),  
  comm     number(7,2),  
  deptNo   number(2,0),  
  constraint pkEmp primary key (empNo),  
  constraint fkDeptNo foreign key (deptNo) references Dept (deptNo)  
);

insert into Dept (deptNo, dName, loc)
values(10, 'ACCOUNTING', 'NEW YORK');
insert into Dept  
values(20, 'RESEARCH', 'DALLAS');
insert into Dept  
values(30, 'SALES', 'CHICAGO');
insert into Dept  
values(40, 'OPERATIONS', 'BOSTON');

insert into Emp  
values(  
 7839, 'KING', 'PRESIDENT', null,  
 '17-11-1981',  
 5000, null, 10  
);
insert into Emp  
values(  
 7698, 'BLAKE', 'MANAGER', 7839,  
 '01-05-1981', 
 2850, null, 30  
);
insert into Emp  
values(  
 7782, 'CLARK', 'MANAGER', 7839,  
 '09-06-1981', 
 2450, null, 10  
);
insert into Emp  
values(  
 7566, 'JONES', 'MANAGER', 7839,  
 '02-04-1981', 
 2975, null, 20  
);
insert into Emp  
values(  
 7788, 'SCOTT', 'ANALYST', 7566,  
 '13-JUL-87',  
 3000, null, 20  
);
insert into Emp  
values(  
 7902, 'FORD', 'ANALYST', 7566,  
 '03-12-1981',  
 3000, null, 20  
);
insert into Emp  
values(  
 7369, 'SMITH', 'CLERK', 7902,  
 '17-12-1980',  
 800, null, 20  
);
insert into Emp  
values(  
 7499, 'ALLEN', 'SALESMAN', 7698,  
 '20-02-1981',  
 1600, 300, 30  
);
insert into Emp  
values(  
 7521, 'WARD', 'SALESMAN', 7698,  
 '22-02-1981',  
 1250, 500, 30  
);
insert into Emp  
values(  
 7654, 'MARTIN', 'SALESMAN', 7698,  
 '28-09-1981',  
 1250, 1400, 30  
);
insert into Emp  
values(  
 7844, 'TURNER', 'SALESMAN', 7698,  
 '08-09-1981', 
 1500, 0, 30  
);
insert into Emp  
values(  
 7876, 'ADAMS', 'CLERK', 7788,  
 '13-07-87',  
 1100, null, 20  
);
insert into Emp  
values(  
 7900, 'JAMES', 'CLERK', 7698,  
 '03-12-1981',  
 950, null, 30  
);
insert into Emp  
values(  
 7934, 'MILLER', 'CLERK', 7782,  
 '23-01-1982',  
 1300, null, 10  
);


select eName, dName, job, empNo, hireDate, loc  
from Emp, Dept  
where Emp.deptNo = Dept.deptNo  
order by eName;