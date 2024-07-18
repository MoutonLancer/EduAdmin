drop table edu_admin;
drop table edu_studentuser;
drop table edu_teacheruser;
drop table edu_leave;
drop table edu_studentscore;
drop table edu_studentcourse;
drop table edu_curriculum;
drop table edu_studentinfo;
drop table edu_teacherinfo;


use eduadmindb;
#管理员表
create table edu_admin(
                          id tinyint primary key auto_increment,
                          username varchar(20) UNIQUE NOT NULL ,
                          password varchar(20) NOT NULL ,
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
                               time_slot enum('1', '2', '3', '4', '5', '6', '7', '8', '9', '10') NOT NULL ,
                               day_of_week enum('1', '2', '3', '4', '5', '6', '7')NOT NULL ,
                               address varchar(20),
                               teacher_id varchar(20) NOT NULL,
                               credits tinyint NOT NULL,
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
                          start_time long NOT NULL comment '请假开始时间',
                          end_time long NOT NULL comment '请假结束时间',
                          application_time long NOT NULL comment '申请时间',
                          approval_time long comment '审批时间',
                          approver varchar(20),
                          CONSTRAINT fk2_studentinfo__studentid FOREIGN KEY(student_id) REFERENCES edu_studentinfo(student_id) ON UPDATE CASCADE ON DELETE CASCADE
);
#学生账号表
create table edu_studentuser(
                                id tinyint primary key auto_increment,
                                username varchar(20) UNIQUE NOT NULL ,
                                password varchar(20) NOT NULL ,
                                student_id varchar(20) NOT NULL default 'S0',
                                register_time datetime
#                                 ,CONSTRAINT fk3_studentInfo__studentId FOREIGN KEY(student_id) REFERENCES edu_studentinfo(student_id) ON UPDATE CASCADE ON DELETE CASCADE
);
#教师账号表
create table edu_teacheruser(
                                id tinyint primary key auto_increment,
                                username varchar(20) UNIQUE NOT NULL ,
                                password varchar(20) NOT NULL ,
                                teacher_id varchar(20) NOT NULL default  0,
                                register_time datetime
#                                 ,CONSTRAINT fk2_teacherInfo__teacherId FOREIGN KEY(teacher_id) REFERENCES edu_teacherinfo(teacher_id) ON UPDATE CASCADE ON DELETE CASCADE
);



insert into edu_admin
values
    (null,'root','pass','A01','2023-03-01');


insert into edu_studentinfo
values
    ('S20230000741','王靖鹏','计算机科学学院','计算机科学与技术','5班','13555555555','浙江省杭州市江干区彭埠街道西桥南路61号','2000-12-21'),
    ('S20230000742','蔡孟良','理学院','化学系','7班','17666666666','北京市海淀区中关村南大街27号','2000-11-19'),
    ('S20230000743','戚俊豪','医学院','生物医学工程系','1班','18000000000','广东省广州市天河区珠江新城华夏路1号','2001-05-21'),
    ('S20230000744','杨清山','美术学院','陶瓷艺术设计系','5班','18111111111','上海市浦东新区浦东南路1108号','1999-07-13');

insert into edu_teacherinfo
values
    ('T20230000881','陈宇轩','计算机科学学院','一级教师','13333333333'),
    ('T20230000882','王嘉佑','理学院','一级教师','13839532342'),
    ('T20230000883','张雨薇','美术学院','副教授','18932511392');

insert into edu_curriculum
values
    (null,'7077','编译原理', '3', '2','7-503','T20230000881',2),
    (null,'8088','物理化学', '1', '5','2-303','T20230000882',1),
    (null,'9099','临床医学', '5', '4','9-411','T20230000883',2);

insert into edu_studentcourse
values
    (null,'S20230000741','7077','0'),
    (null,'S20230000741','8088','1'),
    (null,'S20230000742','7077','0'),
    (null,'S20230000742','9099','1'),
    (null,'S20230000743','8088','1'),
    (null,'S20230000744','9099','0');

insert into edu_studentscore
values
#     (null,'7077','编译原理','T20230000881','S20230000741',-1),
    (null,'8088','物理化学','T20230000882','S20230000741',-1),
#     (null,'7077','编译原理','T20230000881','S20230000742',-1),
    (null,'9099','临床医学','T20230000883','S20230000742',-1),
    (null,'8088','物理化学','T20230000882','S20230000743',-1)
#     (null,'9099','临床医学','T20230000883','S20230000744',-1)
;

insert into edu_leave
values
    (null,'S20230000741','病假','1',1681274833453,1681620433000,1681074825000,1681074833000,'陈宇轩'),
    (null,'S20230000742','外出面试','-1',1681274833555,1681620433000,1681074825000,1681074833000,'张雨薇'),
    (null,'S20230000742','无','0',1681274833000,1681620433000,1681074753000,1681074833000,''),
    (null,'S20230000743','无','0',1681274833000,1681620433000,1681074003000,1681074833000,''),
    (null,'S20230000744','外出面试','0',1681274833000,1681620433000,1681074003000,1681074833000,''),
    (null,'S20230000741','外出面试','0',1681274833000,1681620433000,1681074153000,1681074833000,'');

insert into edu_teacheruser
values
    (null,'t1','t1','T20230000881','2023-06-09'),
    (null,'t2','t2','T20230000882','2023-12-14'),
    (null,'t3','t3','T20230000883','2023-05-23');


insert into edu_studentuser
values
    (null,'s1','s1','S20230000741','2023-01-11'),
    (null,'s2','s2','S20230000742','2023-02-12'),
    (null,'s3','s3','S20230000743','2023-03-13'),
    (null,'s4','s4','S20230000744','2023-04-14');






