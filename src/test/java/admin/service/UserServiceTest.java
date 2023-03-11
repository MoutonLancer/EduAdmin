package admin.service;

import admin.Utils.MyUtils;
import admin.domain.User;
import admin.service.functionService.LoginService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    LoginService loginService;

    @Test
    public void getByInfoTest(){
        userService.getByInfo(null,"null","","","").getRecords().forEach(System.out::println);
        userService.getByInfo(null,"root","","","").getRecords().forEach(System.out::println);
        userService.getByInfo(null,"c","","","").getRecords().forEach(System.out::println);
        userService.getByInfo(null,"","","1","").getRecords().forEach(System.out::println);
        userService.getByInfo(null,"","","","3").getRecords().forEach(System.out::println);
        userService.getByInfo(null,"c","","","3").getRecords().forEach(System.out::println);
    }

    @Test
    public void loginTest(){
        System.out.println(loginService.login(null,null));
        System.out.println(loginService.login("null","null"));
        System.out.println(loginService.login("",""));
        System.out.println(loginService.login(null,"null"));
        System.out.println(loginService.login("","null"));
        System.out.println(loginService.login("",null));



    }

}
