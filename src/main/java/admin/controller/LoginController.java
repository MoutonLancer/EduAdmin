package admin.controller;

import admin.Utils.JwtUtil;
import admin.domain.protocol.Result;
import admin.domain.User;
import admin.service.UserService;
import admin.service.functionService.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/userFun")
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    UserService userService;
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        Integer userId =  loginService.login(user.getUsername(),user.getPassword());
        boolean loginState = userId!=null;

        if(loginState){
            String token = JwtUtil.createJWT(userId.toString());
            return new Result<>().accessTokenResult(token).setMessage("登陆成功");
        }
        return Result.NEED_TOKEN.setMessage("用户名或密码错误");
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user){
        boolean registerState = loginService.register(user.getUsername(),user.getPassword());

        if (registerState)
            return Result.SUCCESS.setMessage("注册成功");
        return Result.FAIL.setMessage("注册失败");
    }

    @GetMapping("/usernameUsable/{username}")
    public Result usernameUsable(@PathVariable String username){
        boolean usable = loginService.usernameUsable(username);
        if (usable)
            return Result.SUCCESS.setMessage("该用户名可用");
        return Result.FAIL.setMessage("用户名不可用");
    }


}
