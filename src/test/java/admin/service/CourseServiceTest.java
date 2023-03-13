package admin.service;

import admin.domain.Course;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CourseServiceTest {
    @Autowired
    CourseService courseService;
    Course testData = new Course(66, "743","8088" );


    @Test
    public void getPageTest(){
        System.out.println(courseService.getPage().getRecords());
        System.out.println(courseService.getPage(1, 3).getRecords());
    }

    @Test
    public void getAllTest(){
        System.out.println(courseService.getAll());
    }
    @Test
    public void getByInfoTest(){
        courseService.getByInfo(1,"7077","740");
        courseService.getByInfo(null,"8088",null);
    }
    @Test
    public void getByPrimaryKeyTest(){
        System.out.println(courseService.getByPrimaryKey(2));
    }

    @Test
    public void saveTest(){
        QueryWrapper<Course> w = new QueryWrapper<Course>()
                .eq("course_id",testData.getCourseId())
                .eq("student_id",testData.getStudentId());
        courseService.remove(w);
        System.out.println(courseService.save(testData));
    }

    @Test
    public void updateTest(){
        courseService.update(testData);
    }

    @Test
    public void removeByPrimaryKeyTest(){

        System.out.println(courseService.removeByPrimaryKey(-1));
    }

}
