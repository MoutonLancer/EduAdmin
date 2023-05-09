package admin.service;

import admin.model.PO.Teacher;
import admin.service.dataService.TeacherInfoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TeacherInfoServiceTest {
    @Autowired
    TeacherInfoService teacherInfoService;
    Teacher testData = new Teacher("8888", "8888", "8888", "8888", "8888");


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
    @Order(3)
    public void saveTest(){
        teacherInfoService.save(testData);
    }

    @Test
    @Order(2)
    public void updateTest(){
        teacherInfoService.update(testData);
    }

    @Test
    @Order(1)
    public void removeByPrimaryKeyTest(){
        teacherInfoService.removeByPrimaryKey(testData.getTeacherId());
    }

}
