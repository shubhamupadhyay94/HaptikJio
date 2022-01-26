package framework.exception;

public class DriverUtilitiesException extends Exception{

    public DriverUtilitiesException() {
    }

    public DriverUtilitiesException(String message) {
        super(message);
    }

    public DriverUtilitiesException(String message, Throwable cause) {
        super(message, cause);
    }

    public DriverUtilitiesException(Throwable cause) {
        super(cause);
    }

    public DriverUtilitiesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
