package admin.domain.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;
    private T data;
    private String message;

    //带响应数据的Result
    public Result<T> okResult(T data){
        return new Result<>(200, data, "");
    }
    public Result<T> okResult(T data, String message){
        return new Result<>(200, data, message);
    }

    //颁发Token的Result      999-access Token , 998-Refresh Token
    public Result accessTokenResult(String token){
        return new Result<>(999, token, "");
    }
    public Result accessTokenResult(String token, String message){
        return new Result<>(999, token, message);
    }
    public Result refreshTokenResult(String token){
        return new Result<>(998, token, "");
    }
    public Result refreshTokenResult(String token, String message){
        return new Result<>(998, token, message);
    }


    public static final Result EMPTY;           //200   请求成功，需要响应内容,但内容为空
    public static final Result SUCCESS;         //204   请求成功，不需要响应内容
    public static final Result NEED_TOKEN;      //401   需进行身份认证，未认证
    public static final Result OVERDUE_TOKEN;   //401   需进行身份认证，认证无效或过期
    public static final Result REFUSE;          //403   服务器理解请求，但拒绝执行
    public static final Result FAIL;            //422   服务器理解请求，但请求失败（数据库操作失败）
    public static final Result ERROR;           //500   服务器异常
    public static final Result NONSUPPORT;      //501   不支持的请求(路径错误)
    static{
        EMPTY = emptyResult();
        SUCCESS = successResult();
        NEED_TOKEN = needTokenResult();
        OVERDUE_TOKEN = overdueTokenResult();
        REFUSE = refuseResult();
        FAIL = failResult();
        ERROR = errorResult();
        NONSUPPORT = nonsupportResult();
    }
    private static Result emptyResult(){
        return new Result(200, null , "Data is empty" );
    }
    private static Result successResult(){
        return new Result(204, null,"Success");
    }
    private static Result needTokenResult(){
        return new Result<>(401, null, "Please provide token");
    }
    private static Result overdueTokenResult(){
        return new Result<>(401, null, "The token has expired");
    }
    private static Result refuseResult(){
        return new Result<>(403, null, "Denial of service");
    }
    private static Result failResult(){
        return new Result<>(422, null, "Request fail");
    }
    private static Result errorResult(){
        return new Result(500, null , "Error" );
    }
    private static Result nonsupportResult(){
        return new Result(501, null , "This request is not support");
    }

    public Result setCode(Integer code) {
        this.code = code;
        return this;
    }
    public Result setData(T data) {
        this.data = data;
        return this;
    }
    public Result setMessage(String message) {
        this.message = message;
        return this;
    }
}
