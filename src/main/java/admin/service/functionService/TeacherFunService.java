package admin.service.functionService;

import admin.Utils.MyUtils;
import admin.model.PO.*;
import admin.model.VO.CurriculumVO;
import admin.model.VO.LeaveVO;
import admin.model.VO.StudentVO;
import admin.service.dataService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//日程安排、请假审批、选课审批、成绩录入
@Service
public class TeacherFunService {
    @Autowired
    private CurriculumService curriculumService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private LeaveService leaveService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherInfoService teacherInfoService;

    public Teacher getTeacherInfo(String teacherId){
        return teacherInfoService.getByPrimaryKey(teacherId);
    }

    //获取日程安排
    public List<CurriculumVO> getCurriculumList(String teacherId){
        List<Curriculum> curriculumList = curriculumService.getByInfo(null, null, null, null, teacherId, null);
        return MyUtils.toVoList(curriculumList, CurriculumVO.class);
    }

    //获取请假数据，未做筛选
    public List<LeaveVO> getLeaveList(String teacherId){
        List<Leave> leaveList = leaveService.getByInfo(null, null, null, null, null, null);
        return MyUtils.toVoList(leaveList, LeaveVO.class);
    }
    //获取选课数据
    public List<Course> getCourseList(String teacherId){
        List<Course> courseList = new ArrayList<Course>();
        List<String> courseIds = curriculumService.getByInfo(null, null, null, null, teacherId, null)
                .stream().map(Curriculum::getCourseId)
                .collect(Collectors.toList());
        courseIds.forEach(courseId -> courseList.addAll(courseService.getByInfo(null, courseId, null, null)));
        return courseList;
    }
    //获取成绩数据
    public List<Score> getScoreList(String teacherId){
        List<Score> scoreList = new ArrayList<Score>();
        List<String> courseIds = curriculumService.getByInfo(null, null, null, null, teacherId, null)
                .stream().map(Curriculum::getCourseId)
                .collect(Collectors.toList());
        courseIds.forEach(courseId -> scoreList.addAll(scoreService.getByInfo(courseId, null, null, null)));
        return scoreList;
    }
    //选课审批
    public boolean courseApproval(Integer id, String  state){
        return courseService.updateState(id,state);
    }
    //请假审批
    public boolean leaveApproval(Integer id, String  state, String teacherId){
        String apporver = teacherInfoService.getByPrimaryKey(teacherId).getTeacherName();
        return leaveService.updateState(id, state, apporver);
    }
    //成绩录入
    public boolean scoreEntry(Integer id, Integer value){
        Score score = scoreService.getByPrimaryKey(id);
        score.setScore(value);
        return scoreService.update(score);
    }
}
