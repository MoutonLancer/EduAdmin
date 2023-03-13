insert into edu_user
values
    (null,'root','pass','teacher','x','2023-03-01'),
    (null,'a','1','student','x','2023-03-01'),
    (null,'b','2','teacher','x','2023-04-01'),
    (null,'c','3','admin'  ,'x','2023-05-01');

insert into edu_studentinfo
values
    ('740','q','q','q','q','q','q','2023-03-01'),
    ('741','w','w','w','w','w','w','2023-03-01'),
    ('742','e','e','e','e','e','e','2023-03-01'),
    ('743','r','r','r','r','r','r','2023-03-01');

insert into edu_teacherinfo
values
    ('1','q','q','q','3573543'),
    ('2','w','w','w','4573543'),
    ('3','e','e','e','5573543'),
    ('4','r','r','r','6573543');

insert into edu_curriculum
values
    (null,'7077','q','q','q',2),
    (null,'8088','w','w','w',5),
    (null,'9099','e','e','e',1);

insert into edu_studentcourse
values
    (null,'740','7077'),
    (null,'740','8088'),
    (null,'741','7077'),
    (null,'742','8088'),
    (null,'742','9099');

insert into edu_studentscore
values
    (null,'7077','name','teaID','740',66),
    (null,'8088','name','teaID','740',77),
    (null,'7077','name','teaID','741',88),
    (null,'8088','name','teaID','742',99),
    (null,'9099','name','teaID','742',100);


