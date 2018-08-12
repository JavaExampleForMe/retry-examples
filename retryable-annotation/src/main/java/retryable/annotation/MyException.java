package retryable.annotation;

public class MyException extends RuntimeException {
    public MyException(String message) {
        super(message);
    }
}