package admin.service;

import admin.service.functionService.AdminLoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminServiceTest {
    @Autowired
    AdminService adminService;
    @Autowired
    AdminLoginService adminLoginService;

    @Test
    public void getByInfoTest(){
        adminService.getByInfo(null,"null","","").getRecords().forEach(System.out::println);
        adminService.getByInfo(null,"root","","").getRecords().forEach(System.out::println);
        adminService.getByInfo(null,"c","","").getRecords().forEach(System.out::println);
        adminService.getByInfo(null,"","","1").getRecords().forEach(System.out::println);
        adminService.getByInfo(null,"","","3").getRecords().forEach(System.out::println);
        adminService.getByInfo(null,"c","","3").getRecords().forEach(System.out::println);
    }

    @Test
    public void loginTest(){
        System.out.println(adminLoginService.login(null,null));
        System.out.println(adminLoginService.login("null","null"));
        System.out.println(adminLoginService.login("",""));
        System.out.println(adminLoginService.login(null,"null"));
        System.out.println(adminLoginService.login("","null"));
        System.out.println(adminLoginService.login("",null));
    }

    @Test
    public void usernameUsabilityTest(){
        System.out.println(adminLoginService.usernameUsable("root"));
        System.out.println(adminLoginService.usernameUsable("a"));
        System.out.println(adminLoginService.usernameUsable(""));
        System.out.println(adminLoginService.usernameUsable("null"));
        System.out.println(adminLoginService.usernameUsable(null));
    }

    @Test
    public void registerTest(){
        System.out.println(adminLoginService.register(null,null));
        System.out.println(adminLoginService.register("null","null"));
        System.out.println(adminLoginService.register("",""));
        System.out.println(adminLoginService.register(null,"null"));
        System.out.println(adminLoginService.register("","null"));
        System.out.println(adminLoginService.register("",null));
        System.out.println("---------------------------------------");
        System.out.println(adminLoginService.register("a","a"));
        System.out.println(adminLoginService.register("b","b"));
        System.out.println(adminLoginService.register("c","c"));
    }

}
