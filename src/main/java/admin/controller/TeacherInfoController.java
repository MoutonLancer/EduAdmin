package admin.controller;

import admin.domain.Teacher;
import admin.domain.protocol.R;
import admin.service.TeacherInfoService;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/teachers")
public class TeacherInfoController {

    @Autowired
    private TeacherInfoService teacherInfoService;

    @GetMapping
    public R getAll(){
        R r = new R();
        List<Teacher> list = teacherInfoService.getAll();
        r.setFlag(!list.isEmpty());
        r.setData(list);
        return r;
    }

    @GetMapping("/{teacherId}")
    public R getByID(@PathVariable String teacherId){
        R r = new R();
        Teacher teacher = teacherInfoService.getByPrimaryKey(teacherId);
        r.setFlag(teacher != null);
        r.setData(teacher);
        return r;
    }

    @GetMapping("_{teacherId}/_{teacherName}/_{department}/_{position}")
    public R getByInfo(@PathVariable String teacherId,@PathVariable String teacherName, @PathVariable String department, @PathVariable String position){
        R r = new R();
        List<Teacher> teachers = teacherInfoService.getByInfo(teacherId, teacherName, department, position);
        r.setFlag(!teachers.isEmpty());
        r.setData(teachers);
        return r;
    }

    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage, @PathVariable int pageSize){
        R r = new R();
        Page<Teacher> page = teacherInfoService.getPage(currentPage, pageSize);
        if (currentPage > page.getPages())//查询的页码大于总页数，重新查询-最后一页
            page = teacherInfoService.getPage((int)page.getPages(), pageSize);
        r.setFlag(!page.getRecords().isEmpty());
        r.setData(page);
        return r;
    }





    @PostMapping
    public R save(@RequestBody Teacher teacher){
        R r = new R();
        r.setFlag(true);
        r.setData(teacherInfoService.save(teacher));
        return r;
    }

    @DeleteMapping("{teacherId}")
    public R delete(@PathVariable String teacherId){
        R r = new R();
        r.setFlag(true);
        r.setData(teacherInfoService.removeByPrimaryKey(teacherId));
        return r;
    }

    @PutMapping
    public R update(@RequestBody Teacher teacher){
        R r = new R();
        r.setFlag(true);
        r.setData(teacherInfoService.update(teacher));
        return r;
    }
}
