package framework.exception;

public class ReadExcelException extends  Exception{

    public ReadExcelException() {
    }

    public ReadExcelException(String message) {
        super(message);
    }

    public ReadExcelException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReadExcelException(Throwable cause) {
        super(cause);
    }

    public ReadExcelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
