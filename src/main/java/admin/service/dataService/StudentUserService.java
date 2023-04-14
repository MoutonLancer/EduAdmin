package admin.service.dataService;

import admin.Utils.MyUtils;
import admin.dao.StudentUserDao;
import admin.domain.StudentUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentUserService extends ServiceImpl<StudentUserDao, StudentUser> implements IService<StudentUser> {
    @Autowired
    private StudentUserDao studentUserDao;
    private final String primaryKey = "id";
    private final Page<StudentUser> page = new Page<>(1, 10);


    public Page<StudentUser> getPage(int currentPage, int pageSize) {
        this.page.setCurrent(currentPage);
        this.page.setSize(pageSize);
        return studentUserDao.selectPage(page, null);
    }
    public Page<StudentUser> getPage() {
        return studentUserDao.selectPage(page, null);
    }
    public List<StudentUser> getAll(){
        return studentUserDao.selectList(null);
    }
    public Page<StudentUser> getByInfo(Integer id, String username, String password, String studentId){
        if (MyUtils.AllParamIsMeaningful(false,id, username, password, studentId))
            return getPage();
        QueryWrapper<StudentUser> w = new QueryWrapper<StudentUser>()
                .eq( MyUtils.AllParamIsMeaningful(true, id),       "id",       id)
                .eq( MyUtils.AllParamIsMeaningful(true, username), "username", username)
                .eq( MyUtils.AllParamIsMeaningful(true, password), "password", password)
                .eq( MyUtils.AllParamIsMeaningful(true, studentId),"student_id",studentId);
        return studentUserDao.selectPage(page,w);
    }
}
