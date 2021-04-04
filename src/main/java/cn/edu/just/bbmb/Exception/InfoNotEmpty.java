package cn.edu.just.bbmb.Exception;

public class InfoNotEmpty extends ServiceException{
    public InfoNotEmpty() {
    }

    public InfoNotEmpty(String message) {
        super(message);
    }

    public InfoNotEmpty(String message, Throwable cause) {
        super(message, cause);
    }

    public InfoNotEmpty(Throwable cause) {
        super(cause);
    }

    public InfoNotEmpty(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
