package admin.service.dataService;


import admin.Utils.MyUtils;
import admin.dao.ScoreDao;
import admin.domain.Score;
import admin.domain.Teacher;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ScoreService extends ServiceImpl<ScoreDao, Score> implements IService<Score> {
    @Autowired
    private ScoreDao scoreDao;
    private final String primaryKey = "id";
    private final Page<Score> page = new Page<>(1, 10);

    public Page<Score> getPage(int currentPage, int pageSize) {
        this.page.setCurrent(currentPage);
        this.page.setSize(pageSize);
        return scoreDao.selectPage(page, null);
    }
    public Page<Score> getPage() {
        return scoreDao.selectPage(page, null);
    }
    public List<Score> getAll(){
        return scoreDao.selectList(null);
    }
    public List<Score> getByInfo(String courseId, String courseName,String teacherId, String studentId){
        if (MyUtils.AllParamIsMeaningful(false, courseId,courseName,teacherId,studentId))
            return getPage().getRecords();
        QueryWrapper<Score> wrapper = new QueryWrapper<Score>()
                .eq( MyUtils.AllParamIsMeaningful(true, courseId),"course_id", courseId)
                .eq( MyUtils.AllParamIsMeaningful(true, courseName),"course_name", courseName)
                .eq( MyUtils.AllParamIsMeaningful(true, teacherId),"teacher_id", teacherId)
                .eq( MyUtils.AllParamIsMeaningful(true, studentId), "student_id", studentId);
        return scoreDao.selectList(wrapper);
    }

    public Score getByPrimaryKey(Integer key){
        QueryWrapper<Score> wrapper = new QueryWrapper<Score>()
                .eq(MyUtils.AllParamIsMeaningful(true, key), primaryKey, key);
        return this.getOne(wrapper);
    }

    public boolean removeByPrimaryKey(Integer key){
        QueryWrapper<Score> wrapper = new QueryWrapper<Score>()
                .eq(MyUtils.AllParamIsMeaningful(true, key), primaryKey, key);
        return scoreDao.delete(wrapper)>0;
    }

    public boolean update(Score score){
        if (score==null) return  false;
        QueryWrapper<Score> wrapper = new QueryWrapper<Score>()
                .eq(primaryKey, score.getId());
        return scoreDao.update(score, wrapper)>0;
    }

}
