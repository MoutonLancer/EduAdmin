package admin.service;

import admin.Utils.MyUtils;
import admin.dao.UserDao;
import admin.domain.Score;
import admin.domain.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService extends ServiceImpl<UserDao,User> implements IService<User> {
    @Autowired
    private UserDao userDao;
    private final String primaryKey = "id";
    private final Page<User> page = new Page<>(1, 10);


    public Page<User> getPage(int currentPage, int pageSize) {
        this.page.setCurrent(currentPage);
        this.page.setSize(pageSize);
        return userDao.selectPage(page, null);
    }
    public Page<User> getPage() {
        return userDao.selectPage(page, null);
    }
    public List<User> getAll(){
        return userDao.selectList(null);
    }
    public Page<User> getByInfo(Integer id, String username, String password, String position, String code){
        if (MyUtils.AllParamIsMeaningful(false,id, username, password, position, code))
            return getPage();
        QueryWrapper<User> w = new QueryWrapper<User>()
                .eq( MyUtils.AllParamIsMeaningful(true, id),       "id",       id)
                .eq( MyUtils.AllParamIsMeaningful(true, username), "username", username)
                .eq( MyUtils.AllParamIsMeaningful(true, password), "password", password)
                .eq( MyUtils.AllParamIsMeaningful(true, position), "position", position)
                .eq( MyUtils.AllParamIsMeaningful(true, code),     "code",     code);
        return userDao.selectPage(page,w);
    }
}
