package admin.controller.Login;

import admin.Utils.JwtUtil;
import admin.domain.Admin;
import admin.domain.protocol.Result;
import admin.service.dataService.AdminService;
import admin.service.login.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/adminLoginFun")
public class AdminLoginController {
    @Autowired
    AdminLoginService adminLoginService;
    @Autowired
    AdminService adminService;

    @PostMapping("/adminLogin")
    public Result adminLogin(@RequestBody Admin admin){
        Integer adminId =  adminLoginService.login(admin.getUsername(), admin.getPassword());
        boolean loginState = (adminId!=null);

        if(loginState){
            String token = JwtUtil.createJWT(adminId.toString());
            return new Result<>().accessTokenResult(token).setMessage("登陆成功");
        }
        return Result.NEED_TOKEN.setMessage("用户名或密码错误");
    }

    //管理员注册
    @PostMapping("/adminRegister")
    public Result register(@RequestBody Admin admin){
        boolean registerState = adminLoginService.register(admin.getUsername(), admin.getPassword());
        return registerState ? Result.SUCCESS.setMessage("注册成功") : Result.FAIL.setMessage("注册失败");
    }
    //管理员用户名可用性校验
    @GetMapping("/adminUsernameUsable/{username}")
    public Result usernameUsable(@PathVariable String username){
        boolean usable = adminLoginService.usernameUsable(username);
        return usable ? Result.SUCCESS.setMessage("该用户名可用") : Result.FAIL.setMessage("用户名不可用");
 }

}
