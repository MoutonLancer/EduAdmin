package admin.service.functionService;

import admin.Utils.Exception.RegisterRollbackException;
import admin.Utils.MyUtils;
import admin.domain.User;
import admin.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class LoginService {
    @Autowired
    UserService userService;

    public String login(String username, String password){
        User user;
        if (MyUtils.AllParamIsMeaningful(true,username,password)) {
            user = userService.getOne(new QueryWrapper<User>()
                    .eq("username", username)
                    .eq("password", password));
            return  user !=null? user.getCode():null;
        }
        return null;
    }

    public boolean usernameUsable(String username){
        if(MyUtils.AllParamIsMeaningful(true,username))
            return 0 == userService.getByInfo(null, username,null,null,null).getTotal();
        return false;
    }

    @Transactional(rollbackFor = RegisterRollbackException.class)  //使用事务,回滚抛出异常
    public boolean register(String username, String password){
        boolean okSave = false, okRegister = false;
        if (MyUtils.AllParamIsMeaningful(true,username,password))
            if (usernameUsable(username))
                okSave =  userService.save(new User(null, username, password,null,null, new Date()));

        if (okSave){
            User dbUser = userService.getOne(new QueryWrapper<User>()
                    .eq("username",username)
                    .eq("password",password));
            dbUser.setCode(MyUtils.codeGenerate(dbUser.getId()));
            okRegister = userService.updateById(dbUser);
        }
        return okRegister;
    }
}
