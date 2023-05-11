package admin.service.dataService;


import admin.Utils.MyUtils;
import admin.dao.CourseDao;
import admin.dao.CurriculumDao;
import admin.dao.ScoreDao;
import admin.model.PO.Course;
import admin.model.PO.Curriculum;
import admin.model.PO.Score;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseService extends ServiceImpl<CourseDao, Course> implements IService<Course> {
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private CurriculumService curriculumService;
    private final String primaryKey = "id";
    private final Page<Course> page = new Page<>(1, 10);


    public Page<Course> getPage(int currentPage, int pageSize) {
        this.page.setCurrent(currentPage);
        this.page.setSize(pageSize);
        return courseDao.selectPage(page, null);
    }
    public Page<Course> getPage() {
        return courseDao.selectPage(page, null);
    }
    public List<Course> getAll(){
        return courseDao.selectList(null);
    }

    public List<Course> getByInfo(Integer id, String courseId, String studentId, String state){
        if (MyUtils.AllParamIsMeaningful(false,id, courseId, studentId, state))
                return getPage().getRecords();
        QueryWrapper<Course> wrapper = new QueryWrapper<Course>()
                .eq( MyUtils.AllParamIsMeaningful(true, id),"id", id)
                .eq( MyUtils.AllParamIsMeaningful(true, courseId),"course_id", courseId)
                .eq( MyUtils.AllParamIsMeaningful(true, studentId), "student_id", studentId)
                .eq( MyUtils.AllParamIsMeaningful(true, state),"state",state);
        return courseDao.selectList(wrapper);
    }

    public Boolean CourseIsExist(String courseId,String studentId){
        return 0 != this.getByInfo(null,courseId,studentId,null).size();
    }

    public Course getByPrimaryKey(Integer key){
        QueryWrapper<Course> wrapper = new QueryWrapper<Course>()
                .eq(MyUtils.AllParamIsMeaningful(true, key), primaryKey, key);
        return this.getOne(wrapper);
    }

    public boolean removeByPrimaryKey(Integer key){
        QueryWrapper<Course> wrapper = new QueryWrapper<Course>()
                .eq(MyUtils.AllParamIsMeaningful(true, key), primaryKey, key);
        return courseDao.delete(wrapper)>0;
    }

    public boolean update(Course course){
        if (course==null) return  false;
        if ("1".equals(course.getState()))
            this.addScore(course.getId());
        QueryWrapper<Course> wrapper = new QueryWrapper<Course>()
                .eq(primaryKey, course.getId());
        return courseDao.update(course, wrapper)>0;
    }

    public boolean updateState(Integer id, String state){
        if (id == null || state == null) return  false;
        if ("1".equals(state))
            this.addScore(id);
        UpdateWrapper<Course> updateWrapper = new UpdateWrapper<Course>()
                .eq("id", id)
                .set("state", state);
        return 1==courseDao.update(null,updateWrapper);
    }

    @Override
    public boolean save(Course course) {
        if ("1".equals(course.getState()))
            this.addScore(course.getId());
        return super.save(course);
    }

    //选课成功则新增成绩记录
    private boolean addScore(Integer id){
        Course course = this.getById(id);
        Curriculum curriculum = curriculumService.getByCourseId(MyUtils.strToInt(course.getCourseId()));
        Score score = new Score(
                null,
                course.getCourseId(),
                curriculum.getCourseName(),
                curriculum.getTeacherId(),
                course.getStudentId(),
                -1
        );
        return scoreService.save(score);
    }
}
