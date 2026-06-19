public class DatabaseException extends Exception {
    private String errorCode;

    public DatabaseException(String message) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}

