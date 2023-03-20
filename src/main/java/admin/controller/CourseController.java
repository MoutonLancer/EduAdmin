package admin.controller;

import admin.domain.Course;
import admin.domain.protocol.R;
import admin.service.CourseService;
import admin.service.CurriculumService;
import admin.service.StudentInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public R getAll(){
        R r = new R();
        List<Course> list = courseService.getAll();
        r.setFlag(!list.isEmpty());
        r.setData(list);
        return r;
    }

    @GetMapping("/{id}")
    public R getByID(@PathVariable Integer id){
        R r = new R();
        Course Course = courseService.getByPrimaryKey(id);
        r.setFlag(Course != null);
        r.setData(Course);
        return r;
    }

    @GetMapping("/_{courseId}/_{studentId}")
    public R getByInfo(@PathVariable String courseId, @PathVariable String studentId){
        R r = new R();
        List<Course> Courses = courseService.getByInfo(null,courseId,studentId);
        r.setFlag(!Courses.isEmpty());
        r.setData(Courses);
        return r;
    }

    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage, @PathVariable int pageSize){
        R r = new R();
        Page<Course> page = courseService.getPage(currentPage, pageSize);
        if (currentPage > page.getPages())//查询的页码大于总页数，重新查询-最后一页
            page = courseService.getPage((int)page.getPages(), pageSize);
        r.setFlag(!page.getRecords().isEmpty());
        r.setData(page);
        return r;
    }





    @PostMapping
    public R save(@RequestBody Course course){
        if (!curriculumService.curriculumIsExist(course.getCourseId()))
            return new R(true,false,"课程信息不存在");
        if (!studentInfoService.studentIsExist(course.getStudentId()))
            return new R(true,false,"学生信息不存在");
        return new R(true,courseService.save(course),"添加成功");

    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id){
        R r = new R();
        r.setFlag(true);
        r.setData(courseService.removeByPrimaryKey(id));
        return r;
    }

    @PutMapping
    public R update(@RequestBody Course Course){
        R r = new R();
        r.setFlag(true);
        r.setData(courseService.update(Course));
        return r;
    }
}
