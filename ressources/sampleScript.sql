drop table if exists person;
create table person (id integer, name string);

insert into person values(3, 'mark');
insert into person values(4, 'yoyo');

select * from person;