package kr.co.ordermanagement.domain.exception;

public class NotCancelableException extends RuntimeException{
    public NotCancelableException(String message){
        super(message);
    }
}
