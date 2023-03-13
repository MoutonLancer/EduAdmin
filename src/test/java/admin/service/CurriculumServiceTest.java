package admin.service;

import admin.domain.Curriculum;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CurriculumServiceTest {
    @Autowired
    CurriculumService curriculumService;
    Curriculum testData = new Curriculum(66, "8888","英语",null,"4",8);


    @Test
    public void getPageTest(){
        System.out.println(curriculumService.getPage().getRecords());
        System.out.println(curriculumService.getPage(1, 3).getRecords());
    }

    @Test
    public void getAllTest(){
        System.out.println(curriculumService.getAll());
    }
    @Test
    public void getByInfoTest(){
        curriculumService.getByInfo("7077",null,"q",null);
        curriculumService.getByInfo(null,"8088",null,null);
    }
    @Test
    public void getByPrimaryKeyTest(){
        System.out.println(curriculumService.getByPrimaryKey(2));
    }

    @Test
    public void saveTest(){
        QueryWrapper<Curriculum> w = new QueryWrapper<Curriculum>()
                .eq("course_id",testData.getCourseId())
                .eq("teacher_id",testData.getTeacherId());
        curriculumService.remove(w);
        System.out.println(curriculumService.save(testData));
    }

    @Test
    public void updateTest(){
        curriculumService.update(testData);
    }

    @Test
    public void removeByPrimaryKeyTest(){

        System.out.println(curriculumService.removeByPrimaryKey(-1));
    }

}
