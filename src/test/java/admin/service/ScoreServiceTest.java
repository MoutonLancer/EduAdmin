package admin.service;

import admin.domain.Score;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ScoreServiceTest {
    @Autowired
    ScoreService scoreService;
    Score testData = new Score(66, "8088", "666", "666", "743", 6);


    @Test
    public void getPageTest(){
        System.out.println(scoreService.getPage().getRecords());
        System.out.println(scoreService.getPage(1, 3).getRecords());
    }

    @Test
    public void getAllTest(){
        System.out.println(scoreService.getAll());
    }
    @Test
    public void getByInfoTest(){
        scoreService.getByInfo("9099",null,null,null);
        scoreService.getByInfo("8088",null,null,null);
    }
    @Test
    public void getByPrimaryKeyTest(){
        System.out.println(scoreService.getByPrimaryKey(2));
    }

    @Test
    public void saveTest(){
        QueryWrapper<Score> w = new QueryWrapper<Score>()
                .eq("course_id",testData.getCourseId())
                .eq("student_id",testData.getStudentId());
        scoreService.remove(w);
        System.out.println(scoreService.save(testData));
    }

    @Test
    public void updateTest(){
        testData.setScore(100);
        scoreService.update(testData);
    }

    @Test
    public void removeByPrimaryKeyTest(){

        System.out.println(scoreService.removeByPrimaryKey(-1));
    }

}
