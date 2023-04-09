package admin.service.functionService;

import admin.Utils.MyUtils;
import admin.domain.User;
import admin.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginService {
    @Autowired
    UserService userService;

    public Integer login(String username, String password){
        User user = null;
        if (MyUtils.AllParamIsMeaningful(true,username,password)) {
            user = userService.getOne(new QueryWrapper<User>()
                    .eq("username", username)
                    .eq("password", password));
            return  user!=null?user.getId():null;
        }
        return null;
    }

    public boolean usernameUsable(String username){
        if(MyUtils.AllParamIsMeaningful(true,username))
            return 0 == userService.getByInfo(null,username,null,null,null).getTotal();
        return false;
    }

    public boolean register(String username, String password){
        if (MyUtils.AllParamIsMeaningful(true,username,password))
            if (usernameUsable(username))
                return userService.save(new User(null,username,password,null,null,new Date()));
        return false;
    }
}
