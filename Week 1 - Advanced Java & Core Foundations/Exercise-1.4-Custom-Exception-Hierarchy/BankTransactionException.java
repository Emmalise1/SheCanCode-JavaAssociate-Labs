public class BankTransactionException extends Exception {
    private String errorCode;

    public BankTransactionException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() { return errorCode; }
<<<<<<< HEAD
}
=======
}
>>>>>>> 1eec90cd047628bbfa03bc0fa14ccbb19fd2a3d6
