package admin.UtilsTest;

import admin.Utils.MyUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyUtilsTest {
    @Test
    public void AllStringIsMeaningfulTest(){
        System.out.print(MyUtils.AllParamIsMeaningful(true));
        System.out.print(MyUtils.AllParamIsMeaningful(true,null));
        System.out.print(MyUtils.AllParamIsMeaningful(true,"null"));
        System.out.print(MyUtils.AllParamIsMeaningful(true,1,"null"));
        System.out.print(MyUtils.AllParamIsMeaningful(true,"null",1));
        System.out.print(MyUtils.AllParamIsMeaningful(true,1,null));
        System.out.println(MyUtils.AllParamIsMeaningful(true,null,1));

        System.out.print(MyUtils.AllParamIsMeaningful(true,"√"));
        System.out.print(MyUtils.AllParamIsMeaningful(true,5));
        System.out.print(MyUtils.AllParamIsMeaningful(true,1,"√"));
        System.out.println(MyUtils.AllParamIsMeaningful(true,"√",1));

        System.out.println("---------------");
        System.out.print(MyUtils.AllParamIsMeaningful(false));
        System.out.print(MyUtils.AllParamIsMeaningful(false,null));
        System.out.println(MyUtils.AllParamIsMeaningful(false,"null"));

        System.out.print(MyUtils.AllParamIsMeaningful(false,1,"null"));
        System.out.print(MyUtils.AllParamIsMeaningful(false,"null",1));
        System.out.print(MyUtils.AllParamIsMeaningful(false,1,null));
        System.out.print(MyUtils.AllParamIsMeaningful(false,null,1));
        System.out.print(MyUtils.AllParamIsMeaningful(false,"√"));
        System.out.print(MyUtils.AllParamIsMeaningful(false,5));
        System.out.print(MyUtils.AllParamIsMeaningful(false,1,"√"));
        System.out.print(MyUtils.AllParamIsMeaningful(false,"√",1));


    }
}
