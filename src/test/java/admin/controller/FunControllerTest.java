package admin.controller;


import admin.controller.functionController.StudentFunController;
import admin.model.pack.StringPack;
import admin.model.protocol.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FunControllerTest {
    @Autowired
    StudentFunController studentFunController;

    @Test
    public void getLeavePageTest(){
        StringPack stringPack = new StringPack("740");
        Result result = studentFunController.getLeavePage(stringPack, 1, 10);
        System.out.println(result);
    }

}
