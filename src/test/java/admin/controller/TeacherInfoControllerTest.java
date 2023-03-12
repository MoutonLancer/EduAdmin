package admin.controller;


import admin.domain.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TeacherInfoControllerTest {
    @Autowired
    TeacherInfoController teacherInfoController;

    @Test
    public void getAllTest(){
        System.out.println(teacherInfoController.getAll());
    }
    @Test
    public void getByInfoTest(){
        System.out.println(teacherInfoController.getByInfo("740", null, null, null));
        System.out.println(teacherInfoController.getByInfo("asdf", null, null, null));
    }
    @Test
    public void getByIdTest(){
        teacherInfoController.getByID("740");
        teacherInfoController.getByID("788");
    }
    @Test
    public void getPageTest(){
        teacherInfoController.getPage(1,2);
    }
    @Test
    public void getSaveTest(){
        Teacher teacher = new Teacher("8888", "8888", "8888", "8888", "8888");
        Teacher teacher2 = new Teacher("9999", "9999", "9999", "9999", "9999");
        System.out.println(teacherInfoController.save(teacher));
        System.out.println(teacherInfoController.save(teacher2));
    }

    @Test
    public void getDeleteTest(){
        System.out.println(teacherInfoController.delete("9999"));
    }
    @Test
    public void getUpdateTest(){
        Teacher teacher = new Teacher("8888", "-", "-", "-", "-");
        System.out.println(teacherInfoController.update(teacher));
        teacherInfoController.delete("8888");
        System.out.println(teacherInfoController.update(teacher));
    }
}
