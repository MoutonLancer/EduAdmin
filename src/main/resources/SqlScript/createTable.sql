
use eduadmindb;
#管理员表
create table edu_admin(
                          id int primary key auto_increment,
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
                               id int primary key auto_increment,
                               course_id varchar(20) NOT NULL,
                               course_name varchar(20) NOT NULL,
                               time_slot enum('1', '2', '3', '4', '5', '6', '7', '8', '9', '10') NOT NULL ,
                               day_of_week enum('1', '2', '3', '4', '5', '6', '7')NOT NULL ,
                               address varchar(20),
                               teacher_id varchar(20) NOT NULL,
                               credits int NOT NULL,
                               UNIQUE KEY UNIQUE_curriculum__course_teacher (course_id,teacher_id)
);

#学生选课表
create table edu_studentcourse(
                                  id int primary key auto_increment,
                                  student_id varchar(20) NOT NULL,
                                  course_id varchar(20)  NOT NULL,
                                  state enum('1','-1','0') NOT NULL default '0' comment '1:通过审核,-1:未通过审核,0:待审核',
                                  UNIQUE KEY UNIQUE_course__course_student (course_id,student_id),
                                  CONSTRAINT fk_studentinfo__studentid FOREIGN KEY(student_id) REFERENCES edu_studentinfo(student_id) ON UPDATE CASCADE ON DELETE CASCADE,
                                  CONSTRAINT fk_curriculum__courseid FOREIGN KEY(course_id) REFERENCES edu_curriculum(course_id) ON UPDATE CASCADE ON DELETE CASCADE
);

#学生成绩表
create table edu_studentscore (
                                  id int primary key auto_increment,
                                  course_id varchar(20) NOT NULL ,
                                  course_name varchar(20)NOT NULL ,
                                  teacher_id varchar(20) NOT NULL ,
                                  student_id varchar(20) NOT NULL ,
                                  score int,
                                  UNIQUE KEY UNIQUE_course__course_student (course_id,student_id),
                                  CONSTRAINT fk_course__unique_courseid_studentid FOREIGN KEY(course_id,student_id) REFERENCES edu_studentcourse(course_id,student_id) ON UPDATE CASCADE ON DELETE CASCADE
);

#请假申请表
create table edu_leave(
                          id int primary key auto_increment,
                          student_id varchar(20) NOT NULL,
                          reason varchar(200) default '无',
                          state enum('1','-1','0') NOT NULL default '0' comment '1:通过审核,-1:未通过审核,0:待审核',
                          start_time int NOT NULL comment '请假开始时间',
                          end_time int NOT NULL comment '请假结束时间',
                          application_time int NOT NULL comment '申请时间',
                          approval_time int comment '审批时间',
                          approver varchar(20),
                          CONSTRAINT fk2_studentinfo__studentid FOREIGN KEY(student_id) REFERENCES edu_studentinfo(student_id) ON UPDATE CASCADE ON DELETE CASCADE
);
#学生账号表
create table edu_studentuser(
                                id int primary key auto_increment,
                                username varchar(20) UNIQUE NOT NULL ,
                                password varchar(20) NOT NULL ,
                                student_id varchar(20) NOT NULL default 'S0',
                                register_time datetime
#     CONSTRAINT fk3_studentInfo__studentId FOREIGN KEY(student_id) REFERENCES edu_studentinfo(student_id) ON UPDATE CASCADE ON DELETE CASCADE
);
#教师账号表
create table edu_teacheruser(
                                id int primary key auto_increment,
                                username varchar(20) UNIQUE NOT NULL ,
                                password varchar(20) NOT NULL ,
                                teacher_id varchar(20) NOT NULL default  0,
                                register_time datetime
#     CONSTRAINT fk2_teacherInfo__teacherId FOREIGN KEY(teacher_id) REFERENCES edu_teacherinfo(teacher_id) ON UPDATE CASCADE ON DELETE CASCADE
);
