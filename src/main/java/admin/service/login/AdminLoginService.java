package admin.service.login;

import admin.Utils.MyUtils;
import admin.model.PO.Admin;
import admin.service.dataService.AdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdminLoginService {
    @Autowired
    AdminService adminService;

    public Integer login(String username, String password){
        Admin admin;
        if (MyUtils.AllParamIsMeaningful(true,username,password)) {
            admin = adminService.getOne(new QueryWrapper<Admin>()
                    .eq("username", username)
                    .eq("password", password));
            return  admin !=null? admin.getId():null;
        }
        return null;
    }

    public boolean usernameUsable(String username){
        if(MyUtils.AllParamIsMeaningful(true,username))
            return 0 == adminService.getByInfo(null, username,null,null).getTotal();
        return false;
    }

    public boolean register(String username, String password){
        if (MyUtils.AllParamIsMeaningful(true,username,password))
            if (usernameUsable(username))
                return adminService.save(new Admin(null,username,password,null,new Date()));
        return false;
    }
}
