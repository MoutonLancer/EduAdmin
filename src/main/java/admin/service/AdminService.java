package admin.service;

import admin.Utils.MyUtils;
import admin.dao.AdminDao;
import admin.domain.Admin;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminService extends ServiceImpl<AdminDao, Admin> implements IService<Admin> {
    @Autowired
    private AdminDao adminDao;
    private final String primaryKey = "id";
    private final Page<Admin> page = new Page<>(1, 10);


    public Page<Admin> getPage(int currentPage, int pageSize) {
        this.page.setCurrent(currentPage);
        this.page.setSize(pageSize);
        return adminDao.selectPage(page, null);
    }
    public Page<Admin> getPage() {
        return adminDao.selectPage(page, null);
    }
    public List<Admin> getAll(){
        return adminDao.selectList(null);
    }
    public Page<Admin> getByInfo(Integer id, String username, String password, String code){
        if (MyUtils.AllParamIsMeaningful(false,id, username, password, code))
            return getPage();
        QueryWrapper<Admin> w = new QueryWrapper<Admin>()
                .eq( MyUtils.AllParamIsMeaningful(true, id),       "id",       id)
                .eq( MyUtils.AllParamIsMeaningful(true, username), "username", username)
                .eq( MyUtils.AllParamIsMeaningful(true, password), "password", password)
                .eq( MyUtils.AllParamIsMeaningful(true, code),     "code",     code);
        return adminDao.selectPage(page,w);
    }
}
