package admin.service.functionService;


import admin.dao.LeaveDao;
import admin.dao.ScoreDao;
import admin.dao.StudentUserDao;
import admin.domain.Leave;
import admin.domain.Score;
import admin.domain.StudentUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentFunService extends ServiceImpl<StudentUserDao, StudentUser> implements IService<StudentUser> {
    @Autowired
    private StudentUserDao studentUserDao;
    @Autowired
    private ScoreDao scoreDao;
    @Autowired
    private LeaveDao leaveDao;

    private final String primaryKey = "id";
    private final Page<Leave> leavePage = new Page<>(1, 10);
    private final Page<Score> scorePage = new Page<>(1, 10);


    public Page<Leave> getLeavePage(String studentId, int currentPage, int pageSize) {
        this.leavePage.setCurrent(currentPage);
        this.leavePage.setSize(pageSize);
        QueryWrapper<Leave> wrapper = new QueryWrapper<Leave>()
                .eq("student_id",studentId);
        return leaveDao.selectPage(leavePage, wrapper);
    }

    public Page<Score> getScorePage(String code, int currentPage, int pageSize) {
        this.scorePage.setCurrent(currentPage);
        this.scorePage.setSize(pageSize);
        QueryWrapper<Score> wrapper = new QueryWrapper<Score>()
                .eq("student_id",code);
        return scoreDao.selectPage(scorePage, wrapper);
    }
}
