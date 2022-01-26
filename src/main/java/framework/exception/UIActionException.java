package framework.exception;

public class UIActionException extends Exception{

    public UIActionException() {
    }

    public UIActionException(String message) {
        super(message);
    }

    public UIActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public UIActionException(Throwable cause) {
        super(cause);
    }

    public UIActionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
