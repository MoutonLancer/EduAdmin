package admin.service.functionService;

import admin.Utils.MyUtils;
import admin.domain.User;
import admin.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    UserService userService;

    public boolean login(String username, String password){
        if (MyUtils.AllParamIsMeaningful(true,username,password))
            return  null != userService.getOne(new QueryWrapper<User>()
                    .eq("username",username)
                    .eq("password",password));
        return false;

    }

    public boolean usernameUsability(String username){
        s
    }
    public boolean register(String username, String password){
        if (MyUtils.AllParamIsMeaningful(true,username,password))

        return false;
    }
}
