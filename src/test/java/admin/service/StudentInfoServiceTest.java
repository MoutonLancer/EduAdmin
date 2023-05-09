package admin.service;

import admin.model.PO.Student;
import admin.service.dataService.StudentInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentInfoServiceTest {
    @Autowired
    StudentInfoService studentInfoService;


    @Test
    public void getPageTest(){
        System.out.println(studentInfoService.getPage().getRecords());
        System.out.println(studentInfoService.getPage(1, 3).getRecords());
    }

    @Test
    public void getAllTest(){
        System.out.println(studentInfoService.getAll());
    }
    @Test
    public void getByInfoTest(){
        studentInfoService.getByInfo("740",null,null,null,null);
    }
    @Test
    public void getByPrimaryKeyTest(){
        studentInfoService.getByPrimaryKey("740");
    }

    @Test
    public void saveTest(){
        Student student = new Student("8888", "8888", "8888", "8888", "8888", "8888", "8888", null);
        studentInfoService.save(student);
    }

    @Test
    public void updateTest(){
        Student student = new Student("8888", "-", "-", "-", "-", "-", "-", null);
        studentInfoService.update(student);
    }

    @Test
    public void removeByPrimaryKeyTest(){
        System.out.println(studentInfoService.removeByPrimaryKey("8888"));
    }

}
