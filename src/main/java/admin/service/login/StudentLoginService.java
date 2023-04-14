package admin.service.login;

import admin.Utils.Exception.RegisterRollbackException;
import admin.Utils.MyUtils;
import admin.domain.StudentUser;
import admin.domain.TeacherUser;
import admin.service.dataService.StudentUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class StudentLoginService {
    @Autowired
    StudentUserService studentUserService;

    public String login(String username, String password){
        StudentUser studentUser;
        if (MyUtils.AllParamIsMeaningful(true,username,password)) {
            studentUser = studentUserService.getOne(new QueryWrapper<StudentUser>()
                    .eq("username", username)
                    .eq("password", password));
            return  studentUser !=null? studentUser.getStudentId():null;
        }
        return null;
    }

    public boolean usernameUsable(String username){
        if(MyUtils.AllParamIsMeaningful(true,username))
            return 0 == studentUserService.getByInfo(null, username,null,null).getTotal();
        return false;
    }

    @Transactional(rollbackFor = RegisterRollbackException.class)  //使用事务,回滚抛出异常
    public boolean register(String username, String password){
        boolean okSave = false, okRegister = false;
        if (MyUtils.AllParamIsMeaningful(true,username,password))
            if (usernameUsable(username))
                okSave =  studentUserService.save(new StudentUser(null, username, password,null, new Date()));

        if (okSave){
            StudentUser dbStudentUser = studentUserService.getOne(new QueryWrapper<StudentUser>()
                    .eq("username",username)
                    .eq("password",password));
            dbStudentUser.setStudentId(MyUtils.codeGenerate(dbStudentUser.getId()));
            okRegister = studentUserService.updateById(dbStudentUser);
        }
        return okRegister;
    }
}
