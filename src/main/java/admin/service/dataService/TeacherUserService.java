package admin.service.dataService;

import admin.Utils.MyUtils;
import admin.dao.TeacherUserDao;
import admin.model.PO.TeacherUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherUserService extends ServiceImpl<TeacherUserDao, TeacherUser> implements IService<TeacherUser> {
    @Autowired
    private TeacherUserDao teacherUserDao;
    private final String primaryKey = "id";
    private final Page<TeacherUser> page = new Page<>(1, 10);


    public Page<TeacherUser> getPage(int currentPage, int pageSize) {
        this.page.setCurrent(currentPage);
        this.page.setSize(pageSize);
        return teacherUserDao.selectPage(page, null);
    }
    public Page<TeacherUser> getPage() {
        return teacherUserDao.selectPage(page, null);
    }
    public List<TeacherUser> getAll(){
        return teacherUserDao.selectList(null);
    }
    public Page<TeacherUser> getByInfo(Integer id, String username, String password, String teacherId){
        if (MyUtils.AllParamIsMeaningful(false,id, username, password, teacherId))
            return getPage();
        QueryWrapper<TeacherUser> w = new QueryWrapper<TeacherUser>()
                .eq( MyUtils.AllParamIsMeaningful(true, id),       "id",       id)
                .eq( MyUtils.AllParamIsMeaningful(true, username), "username", username)
                .eq( MyUtils.AllParamIsMeaningful(true, password), "password", password)
                .eq( MyUtils.AllParamIsMeaningful(true, teacherId),"teacher_id",teacherId);
        return teacherUserDao.selectPage(page,w);
    }
}
