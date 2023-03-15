package admin.config;

import admin.domain.protocol.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
@Slf4j
public class ProjectExceptionAdvice {
    @ExceptionHandler
    public R doException(Exception ex){
        log.warn(ex.getMessage(),ex);
        ex.printStackTrace();
        return new R(false,null,"服务器异常");
    }
}
