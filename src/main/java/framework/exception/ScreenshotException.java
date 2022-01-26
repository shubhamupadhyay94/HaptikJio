package framework.exception;

public class ScreenshotException extends Exception{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ScreenshotException() {
    }

    public ScreenshotException(String message) {
        super(message);
    }

    public ScreenshotException(Throwable cause) {
        super(cause);
    }

    public ScreenshotException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScreenshotException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
