package admin.dao;

import admin.model.PO.Curriculum;
import admin.model.PO.Score;
import admin.model.PO.Student;
import admin.model.PO.Teacher;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DaoUsabilityTest {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private StudentInfoDao studentInfoDao;
    @Autowired
    private TeacherInfoDao teacherInfoDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private CurriculumDao curriculumDao;
    @Autowired
    private ScoreDao scoreDao;

    @Test
    public void user(){
        adminDao.selectById(4);
    }
    @Test
    public void student(){
        studentInfoDao.selectList(new QueryWrapper<Student>().eq("student_id",742));
    }
    @Test
    public void teacher(){
        teacherInfoDao.selectList(new QueryWrapper<Teacher>().eq("teacher_id",1));
    }
    @Test
    public void course(){
        courseDao.selectById(1);
    }
    @Test
    public void curriculum(){
        curriculumDao.selectList(new QueryWrapper<Curriculum>().eq("course_id",9099));
    }
    @Test
    public void score(){
        scoreDao.selectList(new QueryWrapper<Score>().ge("score",80));
    }
}
