package admin.controller.dataController;

import admin.domain.Curriculum;
import admin.domain.protocol.Result;
import admin.service.dataService.CurriculumService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/curriculums")
public class CurriculumController {

    @Autowired
    private CurriculumService curriculumService;

    @GetMapping
    public Result getAll(){
        List<Curriculum> curriculumAll = curriculumService.getAll();
        if(curriculumAll.isEmpty())
            return Result.EMPTY;
        return new Result<>().okResult(curriculumAll);
    }

    @GetMapping("/{id}")
    public Result getByID(@PathVariable Integer id){
        Curriculum curriculum = curriculumService.getByPrimaryKey(id);
        if(curriculum == null)
            return Result.EMPTY;
        return new Result<>().okResult(curriculum);
    }

    @GetMapping("/_{courseId}/_{courseName}/_{teacherId}/_{dayOfWeek}/_{timeSlot}/_{address}")
    public Result getByInfo(@PathVariable String courseId,
                            @PathVariable String courseName,
                            @PathVariable String teacherId,
                            @PathVariable String dayOfWeek,
                            @PathVariable String timeSlot,
                            @PathVariable String address){
        List<Curriculum> curriculums = curriculumService.getByInfo(courseId,courseName,dayOfWeek,timeSlot,teacherId,address);
        if(curriculums.isEmpty())
            return Result.EMPTY;
        return new Result<>().okResult(curriculums);
    }

    @GetMapping("/{currentPage}/{pageSize}")
    public Result getPage(@PathVariable int currentPage, @PathVariable int pageSize){
        Page<Curriculum> page = curriculumService.getPage(currentPage, pageSize);
        if (currentPage > page.getPages())//查询的页码大于总页数，重新查询-最后一页
            page = curriculumService.getPage((int)page.getPages(), pageSize);

        if(page.getRecords().isEmpty())
            return Result.EMPTY;
        return new Result<>().okResult(page);
    }

    @PostMapping
    public Result save(@RequestBody Curriculum curriculum){
        if (curriculumService.save(curriculum))
            return Result.SUCCESS.setMessage("添加成功");
        return Result.FAIL.setMessage("添加失败");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        if (curriculumService.removeByPrimaryKey(id))
            return Result.SUCCESS.setMessage("删除成功");
        return Result.FAIL.setMessage("删除失败");
    }

    @PutMapping
    public Result update(@RequestBody Curriculum curriculum){
        if (curriculumService.update(curriculum))
            return Result.SUCCESS.setMessage("更新成功");
        return Result.FAIL.setMessage("更新失败");
    }
}
