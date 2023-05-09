package admin.advice;

import admin.Utils.JwtUtil;
import admin.model.pack.StringPack;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class UserCodeAdvice {

    @Pointcut("execution(public * admin.controller.functionController.StudentFunController.*(..))")
    public void studentPointCut() {
    }
    @Pointcut("execution(public * admin.controller.functionController.TeacherFunController.*(..))")
    public void teacherPointCut() {
    }

    @Before("studentPointCut()")
    public void setStudentId(JoinPoint joinPoint) {
            //从Token获取studentId
            String studentId = getIdByRequest("userId");
            if (studentId == null)
                return;
            //获取controller参数并赋值
            Object[] args = joinPoint.getArgs();
            StringPack stringPack = (StringPack) args[0];
            stringPack.setValue(studentId);
    }

    @Before("teacherPointCut()")
    public void setTeacherId(JoinPoint joinPoint) {
        //从Token获取studentId
        String teacherId = getIdByRequest("userId");
        if (teacherId == null)
            return;
        //获取controller参数并赋值
        Object[] args = joinPoint.getArgs();
        StringPack stringPack = (StringPack) args[0];
        stringPack.setValue(teacherId);
    }



    private String getIdByRequest(String idKey) {
        //获取上下文request
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            //获取token并解析
            HttpServletRequest req = requestAttributes.getRequest();
            String token = req.getHeader("Authorization");
            Claims claims = JwtUtil.parseJWT(token);
            return  (String) claims.get(idKey);
        }
        return null;
    }
}
