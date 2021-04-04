package cn.edu.just.bbmb.Exception;

public class ValidnameException extends ServiceException{
    public ValidnameException() {
    }

    public ValidnameException(String message) {
        super(message);
    }

    public ValidnameException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidnameException(Throwable cause) {
        super(cause);
    }

    public ValidnameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
