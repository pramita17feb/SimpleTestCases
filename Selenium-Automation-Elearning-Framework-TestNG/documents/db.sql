create table login(
username varchar(50) not null,
password varchar(50));


insert into login values("admin", "admin@123"); 
insert into login values("naveen", "testing@123"); 



create table mcq_question(
question varchar(50) not null,
option1 varchar(50),
option2 varchar(50),
option3 varchar(50),
option4 varchar(50),
answerOptn varchar(50),
answer varchar(50));


insert into mcq_question values("which course your learning", "selenium", "java", "c", "c#", "1", "selenium"); 
insert into mcq_question values("which language are you using in selenium", "python", "java", "c", "c#", "2", "java"); 
