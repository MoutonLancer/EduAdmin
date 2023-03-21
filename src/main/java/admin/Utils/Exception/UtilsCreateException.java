package admin.Utils.Exception;

public class UtilsCreateException extends Exception{
    public UtilsCreateException() {
        super();
    }
    public UtilsCreateException(String message) {
        super(message);
    }
    public UtilsCreateException(Throwable cause) {
        super(cause);
    }
    public UtilsCreateException(String message, Throwable cause) {
        super(message, cause);
    }
    protected UtilsCreateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
