package admin.config;

import admin.Utils.Exception.RegisterRollbackException;
import admin.domain.protocol.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
@Slf4j
public class ProjectExceptionAdvice {
    @ExceptionHandler
    public Result doException(Exception ex){
        log.warn(ex.getMessage(),ex);
        ex.printStackTrace();
        return Result.ERROR.setMessage("服务器异常");
    }

    @ExceptionHandler
    public Result doRegisterException(RegisterRollbackException ex){
        log.warn(ex.getMessage(),ex);
        ex.printStackTrace();
        return Result.FAIL.setMessage("注册失败");
    }

    @ExceptionHandler
    public Result doSQLException(SQLException ex){
        log.warn(ex.getMessage(),ex);
        ex.printStackTrace();
        return Result.FAIL.setMessage("数据库操作失败");
    }

    @ExceptionHandler
    public Result doDataIntegrityViolationException(DataIntegrityViolationException ex){
        log.warn(ex.getMessage(),ex);
        ex.printStackTrace();
        return Result.FAIL.setMessage("数据库操作失败");
    }

}
