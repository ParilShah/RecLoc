package csc258.exceptions;

/**
 * Created by desair4 on 4/21/2017.
 */
public class SaveUserFailedException extends Exception {
    public SaveUserFailedException() {
    }

    public SaveUserFailedException(String message) {
        super(message);
    }

    public SaveUserFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SaveUserFailedException(Throwable cause) {
        super(cause);
    }

    public SaveUserFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
