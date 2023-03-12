package admin.controller;

import admin.controller.protocol.R;
import admin.domain.User;
import admin.service.functionService.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@ResponseBody
@RequestMapping("/userFun")
public class LoginController {
    @Autowired
    LoginService loginService;
    @PostMapping("/login")
    public R login(@RequestBody User user){
        R r = new R();
        System.out.println(user);
        boolean loginState =  loginService.login(user.getUsername(),user.getPassword());
        r.setFlag(true);
        r.setData(loginState);
        r.setMessage(loginState?"登录成功":"用户名或密码错误");
        return r;
    }

    @PostMapping("/register")
    public  R register(@RequestBody User user){
        R r = new R();
        boolean registerState = loginService.register(user.getUsername(),user.getPassword());
        r.setFlag(true);
        r.setData(registerState);
        r.setMessage(registerState?"注册成功":"注册失败");
        return r;
    }

    @GetMapping("/usernameUsable/{username}")
    public R usernameUsable(@PathVariable String username){
        R r = new R();
        boolean usable = loginService.usernameUsable(username);
        r.setFlag(true);
        r.setData(usable);
        r.setMessage(usable?"该用户名可用":"用户名已被占用");
        return r;

    }


}
