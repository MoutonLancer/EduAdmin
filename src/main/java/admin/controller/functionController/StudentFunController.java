package admin.controller.functionController;

import admin.domain.Leave;
import admin.domain.Score;
import admin.domain.Student;
import admin.domain.pack.StringPack;
import admin.domain.protocol.Result;
import admin.service.dataService.ScoreService;
import admin.service.functionService.StudentFunService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/students")
public class StudentFunController {
    @Autowired
    StudentFunService studentFunService;

    @GetMapping("/info")
    public Result getUserInfo(){
        //响应用户身份，code,姓名
        return null;
    }

    @GetMapping("/course")
    public Result getCourse(){
        //响应对应的课程安排-学生上课
        return null;
    }
    //响应请假记录
    @GetMapping("/leave/{currentPage}/{pageSize}")
    public Result getLeavePage(StringPack codePack, @PathVariable int currentPage, @PathVariable int pageSize){
        String code = codePack.getValue();
        Page<Leave> page = studentFunService.getLeavePage(code, currentPage, pageSize);
        if (currentPage > page.getPages())//查询的页码大于总页数，重新查询-最后一页
            page = studentFunService.getLeavePage(code, (int)page.getPages(), pageSize);
        if(page.getRecords().isEmpty())
            return Result.EMPTY;
        return new Result<>().okResult(page);
    }

    //响应成绩信息
    @GetMapping("/score/{currentPage}/{pageSize}")
    public Result getScorePage(StringPack codePack, @PathVariable int currentPage, @PathVariable int pageSize){
        String code = codePack.getValue();
        Page<Score> page = studentFunService.getScorePage(code, currentPage, pageSize);
        if (currentPage > page.getPages())//查询的页码大于总页数，重新查询-最后一页
            page = studentFunService.getScorePage(code, (int)page.getPages(), pageSize);

        if(page.getRecords().isEmpty())
            return Result.EMPTY;
        return new Result<>().okResult(page);
    }
    @GetMapping("/curriculum")
    public Result getCurriculum(){
        //响应课程列表——以供选课
        return null;
    }

    @PostMapping("/courseSelection")
    public Result courseSelection(){
        //选课请求处理
        return null;
    }

    @PatchMapping("/undoCourseSelect")
    public Result undoCourseSelect(){
        //撤销选课申请
        return null;
    }

    @PatchMapping("/undoLeave")
    public Result undoLeave(){
        //撤销未审批请假申请
        return null;
    }
}
