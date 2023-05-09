package admin.service.login;

import admin.Utils.Exception.RegisterRollbackException;
import admin.Utils.MyUtils;
import admin.model.PO.TeacherUser;
import admin.service.dataService.TeacherUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class TeacherLoginService {
    @Autowired
    TeacherUserService teacherUserService;

    public String login(String username, String password){
        TeacherUser teacherUser;
        if (MyUtils.AllParamIsMeaningful(true,username,password)) {
            teacherUser = teacherUserService.getOne(new QueryWrapper<TeacherUser>()
                    .eq("username", username)
                    .eq("password", password));
            return  teacherUser !=null? teacherUser.getTeacherId():null;
        }
        return null;
    }

    public boolean usernameUsable(String username){
        if(MyUtils.AllParamIsMeaningful(true,username))
            return 0 == teacherUserService.getByInfo(null, username,null,null).getTotal();
        return false;
    }

    @Transactional(rollbackFor = RegisterRollbackException.class)  //使用事务,回滚抛出异常
    public boolean register(String username, String password){
        boolean okSave = false, okRegister = false;
        if (MyUtils.AllParamIsMeaningful(true,username,password))
            if (usernameUsable(username))
                okSave =  teacherUserService.save(new TeacherUser(null, username, password,null, new Date()));

        if (okSave){
            TeacherUser dbTeacherUser = teacherUserService.getOne(new QueryWrapper<TeacherUser>()
                    .eq("username",username)
                    .eq("password",password));
            dbTeacherUser.setTeacherId(MyUtils.codeGenerate(true, dbTeacherUser.getId()));
            okRegister = teacherUserService.updateById(dbTeacherUser);
        }
        return okRegister;
    }
}
