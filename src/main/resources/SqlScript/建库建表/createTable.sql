
use eduadmindb;
#用户表
create table edu_user(
    id tinyint primary key auto_increment,
    username varchar(20),
    password varchar(20),
    position varchar(20),
    code varchar(20),
    register_time datetime
);

#学生基本信息表
create table edu_studentInfo(
    student_id varchar(20)  primary key ,
    student_name varchar(20) ,
    department varchar(20),
    subject varchar(20),
    clas varchar(20),
    number varchar(20),
    address varchar(20),
    birthday date
);

#教师基本信息表
create table edu_teacherInfo(
    teacher_id   varchar(20) primary key ,
    teacher_name varchar(20),
    department  varchar(20),
    position    varchar(20),
    number      varchar(20)
);

#课程信息表
create table edu_curriculum(
    course_id varchar(20) primary key ,
    course_name varchar(20),
    address varchar(20),
    teacher_name varchar(20),
    credits tinyint
);

#学生选课表
create table edu_StudentCourse(
    id tinyint primary key auto_increment,
    student_id varchar(20),
    course_id varchar(20),
    CONSTRAINT fk_student_id    FOREIGN KEY(student_id) REFERENCES edu_studentinfo(student_id),
    CONSTRAINT fk_curriculum_id FOREIGN KEY(course_id) REFERENCES edu_curriculum(course_id)
);

#学生成绩表
create table edu_studentScore (
    course_id varchar(20),
    course_name varchar(20),
    teacher_id varchar(20),
    student_id varchar(20),
    score tinyint,
    CONSTRAINT fk_stu_id FOREIGN KEY(student_id) REFERENCES edu_studentinfo(student_id),
    CONSTRAINT fk_cur_id FOREIGN KEY(course_id) REFERENCES edu_curriculum(course_id)
);