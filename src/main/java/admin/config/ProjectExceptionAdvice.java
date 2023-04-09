package admin.config;

import admin.domain.protocol.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ProjectExceptionAdvice {
    @ExceptionHandler
    public Result doException(Exception ex){
        log.warn(ex.getMessage(),ex);
        ex.printStackTrace();
        return Result.ERROR.setMessage("服务器异常");
    }
}
