package admin.controller.Login;

import admin.Utils.JwtUtil;
import admin.model.PO.StudentUser;
import admin.model.protocol.Result;
import admin.service.dataService.StudentUserService;
import admin.service.login.StudentLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/studentLoginFun")
public class StudentLoginController {
    @Autowired
    StudentLoginService studentLoginService;
    @Autowired
    StudentUserService studentUserService;

    @PostMapping("/login")
    public Result login(@RequestBody StudentUser studentUser){
        String userCode =  studentLoginService.login(studentUser.getUsername(), studentUser.getPassword());
        boolean loginState = (userCode!=null);
        if(loginState){
            String token = JwtUtil.createJWT(userCode);
            return new Result<>().accessTokenResult(token).setMessage("登陆成功");
        }
        return Result.NEED_TOKEN.setMessage("用户名或密码错误");
    }


    @PostMapping("/register")
    public Result register(@RequestBody StudentUser studentUser){
        boolean registerState = studentLoginService.register(studentUser.getUsername(), studentUser.getPassword());
        return registerState ? Result.SUCCESS.setMessage("注册成功") : Result.FAIL.setMessage("注册失败");
    }


    @GetMapping("/usernameUsable/{username}")
    public Result usernameUsable(@PathVariable String username){
        boolean usable = studentLoginService.usernameUsable(username);
        return usable ? Result.SUCCESS.setMessage("该用户名可用") : Result.FAIL.setMessage("用户名不可用");
    }
}
