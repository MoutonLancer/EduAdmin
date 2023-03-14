package admin.service;


import admin.Utils.MyUtils;
import admin.dao.CurriculumDao;
import admin.domain.Curriculum;
import admin.domain.Score;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CurriculumService extends ServiceImpl<CurriculumDao, Curriculum> implements IService<Curriculum> {
    @Autowired
    private CurriculumDao curriculumDao;
    private final String primaryKey = "id";
    private final Page<Curriculum> page = new Page<>(1, 10);

    public Page<Curriculum> getPage(int currentPage, int pageSize) {
        this.page.setCurrent(currentPage);
        this.page.setSize(pageSize);
        return curriculumDao.selectPage(page, null);
    }
    public Page<Curriculum> getPage() {
        return curriculumDao.selectPage(page, null);
    }
    public List<Curriculum> getAll(){
        return curriculumDao.selectList(null);
    }
    public List<Curriculum> getByInfo(String courseId, String courseName, String teacherId, String address){
        if (MyUtils.AllParamIsMeaningful(false, courseId,courseName,address,teacherId))
            return getPage().getRecords();
        QueryWrapper<Curriculum> wrapper = new QueryWrapper<Curriculum>()
                .eq( MyUtils.AllParamIsMeaningful(true, courseId),"course_id", courseId)
                .eq( MyUtils.AllParamIsMeaningful(true, courseName),"course_name", courseName)
                .eq( MyUtils.AllParamIsMeaningful(true, teacherId), "teacher_id", teacherId)
                .eq( MyUtils.AllParamIsMeaningful(true, address),"address", address);
        return curriculumDao.selectList(wrapper);
    }



    public Curriculum getByPrimaryKey(Integer key){
        QueryWrapper<Curriculum> wrapper = new QueryWrapper<Curriculum>()
                .eq(MyUtils.AllParamIsMeaningful(true, key), primaryKey, key);
        return this.getOne(wrapper);
    }

    public boolean removeByPrimaryKey(Integer key){
        QueryWrapper<Curriculum> wrapper = new QueryWrapper<Curriculum>()
                .eq(MyUtils.AllParamIsMeaningful(true, key), primaryKey, key);
        return curriculumDao.delete(wrapper)>0;
    }

    public boolean update(Curriculum curriculum){
        if (curriculum==null) return  false;
        QueryWrapper<Curriculum> wrapper = new QueryWrapper<Curriculum>()
                .eq(primaryKey, curriculum.getId());
        return curriculumDao.update(curriculum, wrapper)>0;
    }

}
