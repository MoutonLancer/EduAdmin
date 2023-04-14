package admin.controller.functionController;

import admin.domain.protocol.Result;
import admin.service.dataService.StudentInfoService;
import admin.service.dataService.TeacherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@ResponseBody
@RequestMapping("/teachers")
public class TeacherFunController {
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
        //响应对应的课程安排-教师授课
        return null;
    }

    @GetMapping("/leave")
    public Result getLeave(){
        //响应请假记录-对应课程
        return null;
    }

    @GetMapping("/score")
    public Result getScore(){
        //响应成绩信息-对应课程
        return null;
    }
    @GetMapping("/curriculum")
    public Result getCurriculum(){
        //响应课程列表——以供选课
        return null;
    }

    @PostMapping("/courseSelection")
    public Result courseSelection(){
        //选课请求处理--批准
        return null;
    }

}
