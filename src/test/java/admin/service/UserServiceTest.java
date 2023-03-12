package admin.service;

import admin.service.functionService.LoginService;
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

    @Test
    public void usernameUsabilityTest(){
        System.out.println(loginService.usernameUsable("root"));
        System.out.println(loginService.usernameUsable("a"));
        System.out.println(loginService.usernameUsable(""));
        System.out.println(loginService.usernameUsable("null"));
        System.out.println(loginService.usernameUsable(null));
    }

    @Test
    public void registerTest(){
        System.out.println(loginService.register(null,null));
        System.out.println(loginService.register("null","null"));
        System.out.println(loginService.register("",""));
        System.out.println(loginService.register(null,"null"));
        System.out.println(loginService.register("","null"));
        System.out.println(loginService.register("",null));
        System.out.println("---------------------------------------");
        System.out.println(loginService.register("a","a"));
        System.out.println(loginService.register("b","b"));
        System.out.println(loginService.register("c","c"));

    }

}
