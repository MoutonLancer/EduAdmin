package admin.controller;

import admin.domain.Score;
import admin.domain.protocol.R;
import admin.service.ScoreService;
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

    @GetMapping
    public R getAll(){
        R r = new R();
        List<Score> list = scoreService.getAll();
        r.setFlag(!list.isEmpty());
        r.setData(list);
        return r;
    }

    @GetMapping("/{id}")
    public R getByID(@PathVariable Integer id){
        R r = new R();
        Score score = scoreService.getByPrimaryKey(id);
        r.setFlag(score != null);
        r.setData(score);
        return r;
    }

    @GetMapping("_{courseId}/_{courseName}/_{studentId}")
    public R getByInfo(@PathVariable String courseId,@PathVariable String courseName, @PathVariable String studentId){
        R r = new R();
        List<Score> scores = scoreService.getByInfo(courseId,courseName,studentId);
        r.setFlag(!scores.isEmpty());
        r.setData(scores);
        return r;
    }

    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage, @PathVariable int pageSize){
        R r = new R();
        Page<Score> page = scoreService.getPage(currentPage, pageSize);
        if (currentPage > page.getPages())//查询的页码大于总页数，重新查询-最后一页
            page = scoreService.getPage((int)page.getPages(), pageSize);
        r.setFlag(!page.getRecords().isEmpty());
        r.setData(page);
        return r;
    }





    @PostMapping
    public R save(@RequestBody Score score){
        R r = new R();
        r.setFlag(true);
        r.setData(scoreService.save(score));
        return r;
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable Integer id){
        R r = new R();
        r.setFlag(true);
        r.setData(scoreService.removeByPrimaryKey(id));
        return r;
    }

    @PutMapping
    public R update(@RequestBody Score score){
        R r = new R();
        r.setFlag(true);
        r.setData(scoreService.update(score));
        return r;
    }
}
