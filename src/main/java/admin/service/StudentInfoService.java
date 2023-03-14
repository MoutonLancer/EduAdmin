package admin.service;


import admin.Utils.MyUtils;
import admin.dao.StudentInfoDao;
import admin.domain.Student;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentInfoService extends ServiceImpl<StudentInfoDao, Student> implements IService<Student> {
    @Autowired
    private StudentInfoDao studentInfoDao;
    private final String primaryKey = "student_id";
    private final Page<Student> page = new Page<>(1, 10);

    public Page<Student> getPage(int currentPage, int pageSize) {
        this.page.setCurrent(currentPage);
        this.page.setSize(pageSize);
        return studentInfoDao.selectPage(page, null);
    }
    public Page<Student> getPage() {
        return studentInfoDao.selectPage(page, null);
    }
    public List<Student> getAll(){
        return studentInfoDao.selectList(null);
    }
    public List<Student> getByInfo(String studentId, String studentName, String department, String subject, String clas){
        if (MyUtils.AllParamIsMeaningful(false,studentId, studentName, department, subject, clas))
            return getPage().getRecords();
        QueryWrapper<Student> wrapper = new QueryWrapper<Student>()
                .eq( MyUtils.AllParamIsMeaningful(true, studentId),"student_id", studentId)
                .eq( MyUtils.AllParamIsMeaningful(true, studentName),"student_name", studentName)
                .eq( MyUtils.AllParamIsMeaningful(true, department),"department", department)
                .eq( MyUtils.AllParamIsMeaningful(true, subject),"subject", subject)
                .eq( MyUtils.AllParamIsMeaningful(true, clas), "clas", clas);
        return studentInfoDao.selectList(wrapper);
    }



    public Student getByPrimaryKey(String key){
        QueryWrapper<Student> wrapper = new QueryWrapper<Student>()
                .eq(MyUtils.AllParamIsMeaningful(true, key), primaryKey, key);
        return this.getOne(wrapper);
    }

    public boolean removeByPrimaryKey(String key){
        QueryWrapper<Student> wrapper = new QueryWrapper<Student>()
                .eq(MyUtils.AllParamIsMeaningful(true, key), primaryKey, key);
        return studentInfoDao.delete(wrapper)>0;
    }

    public boolean update(Student student){
        if (student == null) return false;
        QueryWrapper<Student> wrapper = new QueryWrapper<Student>()
                .eq(primaryKey, student.getStudentId());
        return studentInfoDao.update(student, wrapper)>0;
    }

}
