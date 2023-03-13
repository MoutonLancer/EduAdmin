
use eduadmindb;
#用户表

create table edu_user(
    id tinyint primary key auto_increment,
    username varchar(20) UNIQUE NOT NULL ,
    password varchar(20) NOT NULL ,
    position enum ('teacher','student','admin') NOT NULL ,
    code varchar(20) NOT NULL,
    register_time datetime
);

#学生基本信息表
create table edu_studentInfo(
    student_id varchar(20)  primary key ,
    student_name varchar(20) NOT NULL,
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
    teacher_name varchar(20) NOT NULL,
    department  varchar(20),
    position    varchar(20),
    number      varchar(20)
);

#课程信息表
create table edu_curriculum(
    id tinyint primary key auto_increment,
    course_id varchar(20) NOT NULL,
    course_name varchar(20) NOT NULL,
    address varchar(20),
    teacher_id varchar(20) NOT NULL,
    credits tinyint NOT NULL ,
    UNIQUE KEY UNIQUE_curriculum__course_teacher (course_id,teacher_id)
);

#学生选课表
create table edu_StudentCourse(
    id tinyint primary key auto_increment,
    student_id varchar(20) NOT NULL,
    course_id varchar(20)  NOT NULL,
    UNIQUE KEY UNIQUE_course__course_student (course_id,student_id),
    CONSTRAINT fk_student_id    FOREIGN KEY(student_id) REFERENCES edu_studentinfo(student_id),
    CONSTRAINT fk_curriculum_id FOREIGN KEY(course_id) REFERENCES edu_curriculum(course_id)
);

#学生成绩表
create table edu_studentScore (
    id tinyint primary key auto_increment,
    course_id varchar(20),
    course_name varchar(20),
    teacher_id varchar(20),
    student_id varchar(20),
    score tinyint,
    UNIQUE KEY UNIQUE_course__course_student (course_id,student_id),
    CONSTRAINT fk_stu_id FOREIGN KEY(student_id) REFERENCES edu_studentinfo(student_id),
    CONSTRAINT fk_cur_id FOREIGN KEY(course_id) REFERENCES edu_curriculum(course_id)
);