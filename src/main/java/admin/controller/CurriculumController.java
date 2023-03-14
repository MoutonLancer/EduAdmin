package admin.controller;

import admin.domain.Curriculum;
import admin.domain.protocol.R;
import admin.service.CurriculumService;
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
    public R getAll(){
        R r = new R();
        List<Curriculum> list = curriculumService.getAll();
        r.setFlag(!list.isEmpty());
        r.setData(list);
        return r;
    }

    @GetMapping("/{id}")
    public R getByID(@PathVariable Integer id){
        R r = new R();
        Curriculum Curriculum = curriculumService.getByPrimaryKey(id);
        r.setFlag(Curriculum != null);
        r.setData(Curriculum);
        return r;
    }

    @GetMapping("_{courseId}/_{courseName}/_{teacherId}/_{address}")
    public R getByInfo(@PathVariable String courseId,@PathVariable String courseName, @PathVariable String teacherId,@PathVariable String address){
        R r = new R();
        List<Curriculum> Curriculums = curriculumService.getByInfo(courseId,courseName,teacherId,address);
        r.setFlag(!Curriculums.isEmpty());
        r.setData(Curriculums);
        return r;
    }

    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage, @PathVariable int pageSize){
        R r = new R();
        Page<Curriculum> page = curriculumService.getPage(currentPage, pageSize);
        if (currentPage > page.getPages())//查询的页码大于总页数，重新查询-最后一页
            page = curriculumService.getPage((int)page.getPages(), pageSize);
        r.setFlag(!page.getRecords().isEmpty());
        r.setData(page);
        return r;
    }





    @PostMapping
    public R save(@RequestBody Curriculum Curriculum){
        R r = new R();
        r.setFlag(true);
        r.setData(curriculumService.save(Curriculum));
        return r;
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id){
        R r = new R();
        r.setFlag(true);
        r.setData(curriculumService.removeByPrimaryKey(id));
        return r;
    }

    @PutMapping
    public R update(@RequestBody Curriculum Curriculum){
        R r = new R();
        r.setFlag(true);
        r.setData(curriculumService.update(Curriculum));
        return r;
    }
}
