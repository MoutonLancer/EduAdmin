package admin.controller;


import admin.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentInfoControllerTest {
    @Autowired
    StudentInfoController studentInfoController;

    @Test
    public void getAllTest(){
        System.out.println(studentInfoController.getAll());
    }
    @Test
    public void getByInfoTest(){
        System.out.println(studentInfoController.getByInfo("740", null, null, null, null));
        System.out.println(studentInfoController.getByInfo("asdf", null, null, null, null));
    }
    @Test
    public void getByIdTest(){
        studentInfoController.getByID("740");
        studentInfoController.getByID("788");
    }
    @Test
    public void getPageTest(){
        studentInfoController.getPage(1,2);
    }
    @Test
    public void getSaveTest(){
        Student student = new Student("8888", "8888", "8888", "8888", "8888", "8888", "8888", null);
        System.out.println(studentInfoController.save(student));
    }

    @Test
    public void getDeleteTest(){
        System.out.println(studentInfoController.delete("8888"));
    }
    @Test
    public void getUpdateTest(){
        Student student = new Student("8888", "-", "-", "-", "-", "-", "-", null);
        System.out.println(studentInfoController.update(student));
        student.setBirthday("errorData");
        System.out.println(studentInfoController.update(student));
    }
}
