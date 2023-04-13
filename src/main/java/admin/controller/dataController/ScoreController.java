package admin.controller.dataController;

import admin.domain.Score;
import admin.domain.protocol.Result;
import admin.service.dataService.CourseService;
import admin.service.dataService.ScoreService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/scores")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private CourseService courseService;
    @GetMapping
    public Result getAll(){
        List<Score> scoreAll = scoreService.getAll();
        if(scoreAll.isEmpty())
            return Result.EMPTY;
        return new Result<>().okResult(scoreAll);
    }

    @GetMapping("/{id}")
    public Result getByID(@PathVariable Integer id){
        Score score = scoreService.getByPrimaryKey(id);
        if(score == null)
            return Result.EMPTY;
        return new Result<>().okResult(score);
    }

    @GetMapping("_{courseId}/_{courseName}/_{teacherId}/_{studentId}")
    public Result getByInfo(@PathVariable String courseId, @PathVariable String courseName, @PathVariable String teacherId, @PathVariable String studentId){
        List<Score> scores = scoreService.getByInfo(courseId,courseName,teacherId,studentId);
        if(scores.isEmpty())
            return Result.EMPTY;
        return new Result<>().okResult(scores);
    }

    @GetMapping("{currentPage}/{pageSize}")
    public Result getPage(@PathVariable int currentPage, @PathVariable int pageSize){
        Page<Score> page = scoreService.getPage(currentPage, pageSize);
        if (currentPage > page.getPages())//查询的页码大于总页数，重新查询-最后一页
            page = scoreService.getPage((int)page.getPages(), pageSize);

        if(page.getRecords().isEmpty())
            return Result.EMPTY;
        return new Result<>().okResult(page);
    }

    @PostMapping
    public Result save(@RequestBody Score score){
        if (!courseService.CourseIsExist(score.getCourseId(),score.getStudentId()))
            return Result.FAIL.setMessage("选课信息不存在");
        if(null != scoreService.getByInfo(score.getCourseId(),null,null,score.getStudentId()))
            return Result.FAIL.setMessage("选课信息不存在");

        if (scoreService.save(score))
            return Result.SUCCESS.setMessage("添加成功");
        return Result.FAIL.setMessage("添加失败");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        if (scoreService.removeByPrimaryKey(id))
            return Result.SUCCESS.setMessage("删除成功");
        return Result.FAIL.setMessage("删除失败");
    }

    @PutMapping
    public Result update(@RequestBody Score score){
        if (scoreService.update(score))
            return Result.SUCCESS.setMessage("更新成功");
        return Result.FAIL.setMessage("更新失败");
    }
}
