package admin.controller.functionController;

import admin.domain.protocol.Result;
import admin.service.dataService.StudentInfoService;
import admin.service.dataService.TeacherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@ResponseBody
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    StudentInfoService studentInfoService;
    @Autowired
    TeacherInfoService teacherInfoService;

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

    @GetMapping("/leave")
    public Result getLeave(){
        //响应请假记录
        return null;
    }

    @GetMapping("/score")
    public Result getScore(){
        //响应成绩信息
        return null;
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
