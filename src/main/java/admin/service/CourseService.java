package admin.service;


import admin.Utils.MyUtils;
import admin.dao.CourseDao;
import admin.domain.Course;
import admin.domain.Score;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    public List<Course> getByInfo(Integer id, String courseId, String studentId){
        if (MyUtils.AllParamIsMeaningful(false,id, courseId, studentId))
            return getPage().getRecords();
        QueryWrapper<Course> wrapper = new QueryWrapper<Course>()
                .eq( MyUtils.AllParamIsMeaningful(true, id),"id", id)
                .eq( MyUtils.AllParamIsMeaningful(true, courseId),"course_id", courseId)
                .eq( MyUtils.AllParamIsMeaningful(true, studentId), "student_id", studentId);
        return courseDao.selectList(wrapper);
    }

    public Boolean CourseIsExist(String courseId,String studentId){
        return 0 != this.getByInfo(null,courseId,studentId).size();
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
        QueryWrapper<Course> wrapper = new QueryWrapper<Course>()
                .eq(primaryKey, course.getId());
        return courseDao.update(course, wrapper)>0;
    }

}
