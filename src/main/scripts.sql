select * from student;
select * from student where age between 10 and 25;
select student.name from student;
select * from student where name like '%J%';
select student.age from student where id = 8;
select * from student where age > id;
select * from student order by age;
select count(id) from student;
select * from student order by id desc limit 5;