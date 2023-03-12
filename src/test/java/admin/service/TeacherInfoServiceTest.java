package admin.service;

import admin.domain.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TeacherInfoServiceTest {
    @Autowired
    TeacherInfoService teacherInfoService;


    @Test
    public void getPageTest(){
        System.out.println(teacherInfoService.getPage().getRecords());
        System.out.println(teacherInfoService.getPage(1, 3).getRecords());
    }

    @Test
    public void getAllTest(){
        teacherInfoService.getAll();
    }
    @Test
    public void getByInfoTest(){
        teacherInfoService.getByInfo("1", "x", null, null);
        teacherInfoService.getByInfo("3", "e", null, null);
    }
    @Test
    public void getByPrimaryKeyTest(){
        teacherInfoService.getByPrimaryKey("2");
    }

    @Test
    public void saveTest(){
        Teacher teacher = new Teacher("8888", "8888", "8888", "8888", "8888");
        teacherInfoService.save(teacher);
    }

    @Test
    public void updateTest(){
        Teacher teacher = new Teacher("8888", "-", "-", "-", "-");
        teacherInfoService.update(teacher);
    }

    @Test
    public void removeByPrimaryKeyTest(){
        teacherInfoService.removeByPrimaryKey("8888");
    }

}
