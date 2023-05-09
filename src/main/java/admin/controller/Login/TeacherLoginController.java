package admin.controller.Login;

import admin.Utils.JwtUtil;
import admin.model.PO.TeacherUser;
import admin.model.protocol.Result;
import admin.service.dataService.TeacherUserService;
import admin.service.login.TeacherLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/teacherLoginFun")
public class TeacherLoginController {
    @Autowired
    TeacherLoginService teacherLoginService;
    @Autowired
    TeacherUserService teacherUserService;

    @PostMapping("/login")
    public Result login(@RequestBody TeacherUser teacherUser){
        String userCode =  teacherLoginService.login(teacherUser.getUsername(), teacherUser.getPassword());
        boolean loginState = (userCode!=null);
        if(loginState){
            String token = JwtUtil.createJWT(userCode);
            return new Result<>().accessTokenResult(token).setMessage("登陆成功");
        }
        return Result.NEED_TOKEN.setMessage("用户名或密码错误");
    }


    @PostMapping("/register")
    public Result register(@RequestBody TeacherUser teacherUser){
        boolean registerState = teacherLoginService.register(teacherUser.getUsername(), teacherUser.getPassword());
        return registerState ? Result.SUCCESS.setMessage("注册成功") : Result.FAIL.setMessage("注册失败");
    }


    @GetMapping("/usernameUsable/{username}")
    public Result usernameUsable(@PathVariable String username){
        boolean usable = teacherLoginService.usernameUsable(username);
        return usable ? Result.SUCCESS.setMessage("该用户名可用") : Result.FAIL.setMessage("用户名不可用");
    }
}
