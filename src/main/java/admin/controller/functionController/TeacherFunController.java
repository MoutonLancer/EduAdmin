package admin.controller.functionController;

import admin.Utils.MyUtils;
import admin.model.PO.Course;
import admin.model.PO.Leave;
import admin.model.PO.Score;
import admin.model.PO.Teacher;
import admin.model.VO.CurriculumVO;
import admin.model.VO.LeaveVO;
import admin.model.VO.StudentVO;
import admin.model.pack.StringPack;
import admin.model.protocol.Result;
import admin.service.dataService.StudentInfoService;
import admin.service.dataService.TeacherInfoService;
import admin.service.functionService.TeacherFunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@RequestMapping("/teachers")
public class TeacherFunController {

    @Autowired
    private TeacherFunService teacherFunService;

    @GetMapping("/info")
    public Result getUserInfo(StringPack codePack){
        //响应教师身份信息
        String code = codePack.getValue();
        Teacher teacher = teacherFunService.getTeacherInfo(code);
        if (teacher == null)
            return Result.EMPTY;
        return new Result<>().okResult(teacher);
    }


    @GetMapping("/curriculum")
    public Result getCurriculum(StringPack codePack){
        //响应日程安排
        String code = codePack.getValue();
        List<CurriculumVO> courseTable = teacherFunService.getCurriculumList(code);
        if(courseTable.isEmpty())
            return Result.EMPTY;
        return new Result<>().okResult(courseTable);
    }

    @GetMapping("/leave")
    public Result getLeave(StringPack codePack){
        //响应请假记录
        String code = codePack.getValue();
        List<LeaveVO> leaveList = teacherFunService.getLeaveList(code);
        if(leaveList.isEmpty())
            return Result.EMPTY;
        return new Result<>().okResult(leaveList);
    }

    @GetMapping("/course")
    public Result getCourse(StringPack codePack){
        //响应选课记录
        String code = codePack.getValue();
        List<Course> courseList = teacherFunService.getCourseList(code);
        if(courseList.isEmpty())
            return Result.EMPTY;
        return new Result<>().okResult(courseList);
    }

    @GetMapping("/score")
    public Result getScore(StringPack codePack){
        String code = codePack.getValue();
        List<Score> scoreList = teacherFunService.getScoreList(code);
        if(scoreList.isEmpty())
            return Result.EMPTY;
        return new Result<>().okResult(scoreList);
    }

    @PatchMapping("/scoreEntry")
    public Result ScoreEntry(StringPack codePack, @RequestBody Map<String,Object> scoreMap){
        //成绩录入
//        String code = codePack.getValue();
        Integer id = (Integer) scoreMap.get("id");
        Integer score = MyUtils.strToInt((String) scoreMap.get("score"));

        if (teacherFunService.scoreEntry(id, score))
            return Result.SUCCESS.setMessage("成绩更新成功");
        return Result.FAIL.setMessage("成绩更新失败");
    }


    @PatchMapping("/leaveApproval")
    public Result leaveApproval(StringPack codePack, @RequestBody Map<String,Object> leaveMap){
        //请假申请处理
        String code = codePack.getValue();
        Integer id = (Integer) leaveMap.get("id");
        String state = (String) leaveMap.get("state");
        if (teacherFunService.leaveApproval(id, state, code))
            return Result.SUCCESS.setMessage("操作成功");
        return Result.FAIL.setMessage("操作失败");
    }

    @PatchMapping("/courseApproval")
    public Result courseApproval(StringPack codePack,@RequestBody Map<String,Object> courseMap){
        //选课申请处理
        Integer id = (Integer) courseMap.get("id");
        String state = (String) courseMap.get("state");
        if (teacherFunService.courseApproval(id, state))
            return Result.SUCCESS.setMessage("操作成功");
        return Result.FAIL.setMessage("操作失败");
    }

}
