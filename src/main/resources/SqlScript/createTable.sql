
use eduadmindb;
#管理员表
create table edu_admin(
    id tinyint primary key auto_increment,
    username varchar(20) UNIQUE NOT NULL ,
    password varchar(20) NOT NULL ,
    code varchar(20) NOT NULL default  0,
    register_time datetime
);
create table edu_user(
    id tinyint primary key auto_increment,
    username varchar(20) UNIQUE NOT NULL ,
    password varchar(20) NOT NULL ,
    position boolean NULL DEFAULT false,
    code varchar(20) NOT NULL default  0,
    register_time datetime
);

#学生基本信息表
create table edu_studentinfo(
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
create table edu_teacherinfo(
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
create table edu_studentcourse(
    id tinyint primary key auto_increment,
    student_id varchar(20) NOT NULL,
    course_id varchar(20)  NOT NULL,
    state enum('1','-1','0') NOT NULL default '0' comment '1:通过审核,-1:未通过审核,0:待审核',
    UNIQUE KEY UNIQUE_course__course_student (course_id,student_id),
    CONSTRAINT fk_studentinfo__studentid FOREIGN KEY(student_id) REFERENCES edu_studentinfo(student_id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_curriculum__courseid FOREIGN KEY(course_id) REFERENCES edu_curriculum(course_id) ON UPDATE CASCADE ON DELETE CASCADE
);

#学生成绩表
create table edu_studentscore (
    id tinyint primary key auto_increment,
    course_id varchar(20) NOT NULL ,
    course_name varchar(20)NOT NULL ,
    teacher_id varchar(20) NOT NULL ,
    student_id varchar(20) NOT NULL ,
    score tinyint,
    UNIQUE KEY UNIQUE_course__course_student (course_id,student_id),
    CONSTRAINT fk_course__unique_courseid_studentid FOREIGN KEY(course_id,student_id) REFERENCES edu_studentcourse(course_id,student_id) ON UPDATE CASCADE ON DELETE CASCADE
);

#请假申请表
create table edu_leave(
    id tinyint primary key auto_increment,
    student_id varchar(20) NOT NULL,
    reason varchar(200) default '无',
    state enum('1','-1','0') NOT NULL default '0' comment '1:通过审核,-1:未通过审核,0:待审核',
    start_time datetime NOT NULL,
    end_time datetime NOT NULL ,
    application_time datetime NOT NULL ,
    approval_time datetime,
    approver varchar(20),
    CONSTRAINT fk2_studentinfo__studentid FOREIGN KEY(student_id) REFERENCES edu_studentinfo(student_id) ON UPDATE CASCADE ON DELETE CASCADE
);








