package admin.controller.functionController;

import admin.Utils.DateFormatUtil;
import admin.model.PO.Leave;
import admin.model.PO.Score;
import admin.model.VO.CurriculumVO;
import admin.model.pack.StringPack;
import admin.model.protocol.Result;
import admin.model.VO.LeaveVO;
import admin.model.VO.StudentVO;
import admin.service.dataService.LeaveService;
import admin.service.functionService.StudentFunService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/students")
public class StudentFunController {
    @Autowired
    StudentFunService studentFunService;
    @Autowired
    LeaveService leaveService;


    //响应用户身份，学号,姓名,专业,班级
    @GetMapping("/info")
    public Result getStudentInfo(StringPack codePack){
        String code = codePack.getValue();
        StudentVO studentVO = studentFunService.getStudentInfo(code);
        if (studentVO == null)
            return Result.EMPTY;
        return new Result<>().okResult(studentVO);
    }

    //响应请假记录
    @GetMapping("/leave/{currentPage}/{pageSize}")
    public Result getLeavePage(StringPack codePack, @PathVariable int currentPage, @PathVariable int pageSize){
        String code = codePack.getValue();
        Page<LeaveVO> page = studentFunService.getLeavePage(code, currentPage, pageSize);
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

    //响应对应的课程安排-学生上课
    @GetMapping("/course")
    public Result getCourse(StringPack codePack){
        String code = codePack.getValue();
        List<CurriculumVO> courseTable = studentFunService.getCourse(code);
        if(courseTable.isEmpty())
            return Result.EMPTY;
        return new Result<>().okResult(courseTable);
    }


    @GetMapping("/curriculum")
    public Result getCurriculum(StringPack codePack){
        //响应课程列表——以供选课
        return null;
    }

    //请假申请处理
    @PostMapping("/leave")
    public Result addLeave(StringPack codePack, @RequestBody Leave leave){
        leave.setStudentId(codePack.getValue());
        leave.setApplicationTime((new Date()).getTime());
        leave.setState("0");

        if(leaveService.save(leave))
            return Result.SUCCESS;
        return Result.FAIL;
    }

    @PostMapping("/courseSelection")
    public Result courseSelection(StringPack codePack){
        //选课请求处理
        return null;
    }

    @PatchMapping("/undoCourseSelect")
    public Result undoCourseSelect(StringPack codePack){
        //撤销选课申请
        return null;
    }

    @PatchMapping("/undoLeave")
    public Result undoLeave(StringPack codePack){
        //撤销未审批请假申请
        return null;
    }
}
