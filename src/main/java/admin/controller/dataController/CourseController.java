package admin.controller.dataController;

import admin.model.PO.Course;
import admin.model.protocol.Result;
import admin.service.dataService.CourseService;
import admin.service.dataService.CurriculumService;
import admin.service.dataService.StudentInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    StudentInfoService studentInfoService;
    @Autowired
    CurriculumService curriculumService;
    @Autowired
    private CourseService courseService;

    @GetMapping
    public Result getAll(){
        List<Course> coursesAll = courseService.getAll();
        if(coursesAll.isEmpty())
            return Result.EMPTY;
        return new Result<>().okResult(coursesAll);
    }

    @GetMapping("/{id}")
    public Result getByID(@PathVariable Integer id){
        Course course = courseService.getByPrimaryKey(id);
        if(course == null)
            return Result.EMPTY;
        return new Result<>().okResult(course);
    }

    @GetMapping("/_{courseId}/_{studentId}/_{state}")
    public Result getByInfo(@PathVariable String courseId, @PathVariable String studentId, @PathVariable String state){
        List<Course> courses = courseService.getByInfo(null,courseId, studentId, state);
        if(courses.isEmpty())
            return Result.EMPTY;
        return new Result<>().okResult(courses);
    }

    @GetMapping("{currentPage}/{pageSize}")
    public Result getPage(@PathVariable int currentPage, @PathVariable int pageSize){
        Page<Course> page = courseService.getPage(currentPage, pageSize);
        if (currentPage > page.getPages())//查询的页码大于总页数，改为重新查询最后一页
            page = courseService.getPage((int)page.getPages(), pageSize);

        if(page.getRecords().isEmpty())
            return Result.EMPTY;
        return new Result<>().okResult(page);
    }

    @PostMapping
    public Result save(@RequestBody Course course){
        if (!curriculumService.curriculumIsExist(course.getCourseId()))
            return Result.FAIL.setMessage("课程信息不存在");
        if (!studentInfoService.studentIsExist(course.getStudentId()))
            return Result.FAIL.setMessage("学生信息不存在");

        if (courseService.save(course))
            return Result.SUCCESS.setMessage("添加成功");
        return Result.FAIL.setMessage("添加失败");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        if (courseService.removeByPrimaryKey(id))
            return Result.SUCCESS.setMessage("删除成功");
        return Result.FAIL.setMessage("删除失败");
    }

    @PutMapping
    public Result update(@RequestBody Course Course){
        if (courseService.update(Course))
            return Result.SUCCESS.setMessage("更新成功");
        return Result.FAIL.setMessage("更新失败");
    }

    @PatchMapping
    public Result updateState(@RequestBody Map<String,Object> map){
        if (courseService.updateState((Integer) map.get("id"),(String) map.get("state")))
            return Result.SUCCESS.setMessage("更新成功");
        return Result.FAIL.setMessage("更新失败");

    }
}
