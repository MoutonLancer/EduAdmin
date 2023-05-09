package admin.service.functionService;

import admin.Utils.MyUtils;
import admin.dao.LeaveDao;
import admin.dao.ScoreDao;
import admin.dao.StudentUserDao;
import admin.model.PO.*;
import admin.model.VO.CurriculumVO;
import admin.model.VO.LeaveVO;
import admin.model.VO.StudentVO;
import admin.service.dataService.CourseService;
import admin.service.dataService.CurriculumService;
import admin.service.dataService.StudentInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentFunService extends ServiceImpl<StudentUserDao, StudentUser> implements IService<StudentUser> {
    @Autowired
    private StudentUserDao studentUserDao;
    @Autowired
    private ScoreDao scoreDao;
    @Autowired
    private LeaveDao leaveDao;
    @Autowired
    private CurriculumService curriculumService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentInfoService studentInfoService;

    private final String primaryKey = "id";
    private final Page<Leave> leavePage = new Page<>(1, 10);
    private final Page<LeaveVO> leaveVOPage = new Page<>(1, 10);
    private final Page<Score> scorePage = new Page<>(1, 10);


    public StudentVO getStudentInfo(String studentId){
        Student student =  studentInfoService.getByPrimaryKey(studentId);
        return new StudentVO().toVO(student);
    }

    public Page<LeaveVO> getLeavePage(String studentId, int currentPage, int pageSize) {
        this.leavePage.setCurrent(currentPage);
        this.leavePage.setSize(pageSize);
        QueryWrapper<Leave> wrapper = new QueryWrapper<Leave>()
                .eq("student_id",studentId);
        leaveDao.selectPage(leavePage, wrapper);

        //PO 转 VO 抹去不必要信息
        BeanUtils.copyProperties(leavePage,leaveVOPage);
        List<LeaveVO> voRecords=leavePage.getRecords().stream().map(po -> new LeaveVO().toVO(po)).collect(Collectors.toList());
        return leaveVOPage.setRecords(voRecords);
    }

    public Page<Score> getScorePage(String code, int currentPage, int pageSize) {
        this.scorePage.setCurrent(currentPage);
        this.scorePage.setSize(pageSize);
        QueryWrapper<Score> wrapper = new QueryWrapper<Score>()
                .eq("student_id",code);
        return scoreDao.selectPage(scorePage, wrapper);
    }

    public List<CurriculumVO> getCourse(String code) {
        //查询选课成功的课程
        List<Course> courses = courseService.getByInfo(null,null, code,"1");
        List<String> courseIds = courses.stream().map(Course::getCourseId).collect(Collectors.toList());

        //查询课程信息
        List<Curriculum> curriculumList = new ArrayList<>();
        courseIds.forEach(courseId ->{
           curriculumList.addAll(curriculumService.getByInfo(courseId,null,null,null,null,null));
        });

        //转为VO
        return MyUtils.toVoList(curriculumList, CurriculumVO.class);
    }

}
