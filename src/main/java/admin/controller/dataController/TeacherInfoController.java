package admin.controller.dataController;

import admin.model.PO.Teacher;
import admin.model.protocol.Result;
import admin.service.dataService.TeacherInfoService;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/teacherInfos")
public class TeacherInfoController {

    @Autowired
    private TeacherInfoService teacherInfoService;

    @GetMapping
    public Result getAll(){
        Result r = new Result();
        List<Teacher> teacherAll = teacherInfoService.getAll();
        if(teacherAll.isEmpty())
            return Result.EMPTY;
        return new Result<>().okResult(teacherAll);
    }

    @GetMapping("/{teacherId}")
    public Result getByID(@PathVariable String teacherId){
        Teacher teacher = teacherInfoService.getByPrimaryKey(teacherId);
        if(teacher == null)
            return Result.EMPTY;
        return new Result<>().okResult(teacher);
    }

    @GetMapping("_{teacherId}/_{teacherName}/_{department}/_{position}")
    public Result getByInfo(@PathVariable String teacherId, @PathVariable String teacherName, @PathVariable String department, @PathVariable String position){
        List<Teacher> teachers = teacherInfoService.getByInfo(teacherId, teacherName, department, position);
        if(teachers.isEmpty())
            return Result.EMPTY;
        return new Result<>().okResult(teachers);
    }

    @GetMapping("{currentPage}/{pageSize}")
    public Result getPage(@PathVariable int currentPage, @PathVariable int pageSize){
        Page<Teacher> page = teacherInfoService.getPage(currentPage, pageSize);
        if (currentPage > page.getPages())//查询的页码大于总页数，重新查询-最后一页
            page = teacherInfoService.getPage((int)page.getPages(), pageSize);

        if(page.getRecords().isEmpty())
            return Result.EMPTY;
        return new Result<>().okResult(page);
    }

    @PostMapping
    public Result save(@RequestBody Teacher teacher){
        if (teacherInfoService.save(teacher))
            return Result.SUCCESS.setMessage("添加成功");
        return Result.FAIL.setMessage("添加失败");
    }

    @DeleteMapping("{teacherId}")
    public Result delete(@PathVariable String teacherId){
        if (teacherInfoService.removeByPrimaryKey(teacherId))
            return Result.SUCCESS.setMessage("删除成功");
        return Result.FAIL.setMessage("删除失败");
    }

    @PutMapping
    public Result update(@RequestBody Teacher teacher){
        if (teacherInfoService.update(teacher))
            return Result.SUCCESS.setMessage("更新成功");
        return Result.FAIL.setMessage("更新失败");
    }
}
