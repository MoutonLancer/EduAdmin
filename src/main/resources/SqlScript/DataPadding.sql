insert into edu_admin
values
    (null,'root','pass','x','2023-03-01');

insert into edu_teacheruser
values
    (null,'t','t','code1','2023-05-01');

insert into edu_studentuser
values
    (null,'s','s','code1','2023-05-01');

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
    ('4','e','e','e','5573543'),
    ('5','e','e','e','5573543'),
    ('6','e','e','e','5573543'),
    ('7','e','e','e','5573543'),
    ('8','r','r','r','6573543'),
    ('9','r','r','r','6573543'),
    ('10','r','r','r','6573543'),
    ('11','r','r','r','6573543'),
    ('12','r','r','r','6573543'),
    ('13','r','r','r','6573543');

insert into edu_curriculum
values
    (null,'7077','q','q','q',2),
    (null,'8088','w','w','w',5),
    (null,'9099','e','e','e',1);

insert into edu_studentcourse
values
    (null,'740','7077','-1'),
    (null,'740','8088','0'),
    (null,'741','7077','1'),
    (null,'742','8088','-1'),
    (null,'742','9099','1');

insert into edu_studentscore
values
    (null,'7077','name','teaID','740',66),
    (null,'8088','name','teaID','740',77),
    (null,'7077','name','teaID','741',88),
    (null,'8088','name','teaID','742',99),
    (null,'9099','name','teaID','742',100);

insert into edu_leave
values
    (null,'740','reason','1',1681274833,1681620433,1681074825,1681074833,'a'),
    (null,'740','reason','-1',1681274833,1681620433,1681074825,1681074833,'a'),
    (null,'741','reason','0',1681274833,1681620433,1681074753,1681074833,'b'),
    (null,'742','reason','0',1681274833,1681620433,1681074003,1681074833,'c'),
    (null,'742','reason','0',1681274833,1681620433,1681074153,1681074833,'d');


