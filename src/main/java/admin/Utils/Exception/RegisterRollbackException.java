package admin.Utils.Exception;

public class RegisterRollbackException extends Exception{
        public RegisterRollbackException() {
            super();
        }
        public RegisterRollbackException(String message) {
            super(message);
        }
        public RegisterRollbackException(Throwable cause) {
            super(cause);
        }
        public RegisterRollbackException(String message, Throwable cause) {
            super(message, cause);
        }
        protected RegisterRollbackException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
}
