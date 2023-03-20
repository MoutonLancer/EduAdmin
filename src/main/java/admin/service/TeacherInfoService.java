package admin.service;


import admin.Utils.MyUtils;
import admin.dao.TeacherInfoDao;
import admin.domain.Student;
import admin.domain.Teacher;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class TeacherInfoService extends ServiceImpl<TeacherInfoDao, Teacher> implements IService<Teacher> {
    @Autowired
    private TeacherInfoDao teacherInfoDao;
    private final String primaryKey = "teacher_id";
    private final Page<Teacher> page = new Page<>(1, 10);

    public Page<Teacher> getPage(int currentPage, int pageSize) {
        this.page.setCurrent(currentPage);
        this.page.setSize(pageSize);
        return teacherInfoDao.selectPage(page, null);
    }
    public Page<Teacher> getPage() {
        return teacherInfoDao.selectPage(page, null);
    }
    public List<Teacher> getAll(){
        return teacherInfoDao.selectList(null);
    }
    public List<Teacher> getByInfo(String teacherId, String teacherName, String department, String position){
        if (MyUtils.AllParamIsMeaningful(false,teacherId, teacherName, department, position))
            return getPage().getRecords();
        QueryWrapper<Teacher> wrapper = new QueryWrapper<Teacher>()
                .eq( MyUtils.AllParamIsMeaningful(true, teacherId),"teacher_id", teacherId)
                .eq( MyUtils.AllParamIsMeaningful(true, teacherName),"teacher_name", teacherName )
                .eq( MyUtils.AllParamIsMeaningful(true, department),"department", department)
                .eq( MyUtils.AllParamIsMeaningful(true, position),"position", position);
        return teacherInfoDao.selectList(wrapper);
    }



    public Teacher getByPrimaryKey(String key){
        QueryWrapper<Teacher> wrapper = new QueryWrapper<Teacher>()
                .eq(MyUtils.AllParamIsMeaningful(true, key),primaryKey, key);
        return this.getOne(wrapper);
    }

    public boolean removeByPrimaryKey(String key){
        QueryWrapper<Teacher> wrapper = new QueryWrapper<Teacher>()
                .eq(MyUtils.AllParamIsMeaningful(true, key),primaryKey, key);
        return teacherInfoDao.delete(wrapper)>0;
    }

    public boolean update(Teacher teacher){
        if (teacher == null) return false;
        QueryWrapper<Teacher> wrapper = new QueryWrapper<Teacher>()
                .eq(primaryKey, teacher.getTeacherId());
        return teacherInfoDao.update(teacher, wrapper)>0;
    }

}
