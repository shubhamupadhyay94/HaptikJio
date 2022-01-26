package framework.exception;

public class ConfigFileReaderException extends Exception{

    public ConfigFileReaderException() {
    }

    public ConfigFileReaderException(String message) {
        super(message);
    }

    public ConfigFileReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigFileReaderException(Throwable cause) {
        super(cause);
    }

    public ConfigFileReaderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
