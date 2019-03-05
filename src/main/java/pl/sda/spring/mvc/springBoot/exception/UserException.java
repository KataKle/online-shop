package pl.sda.spring.mvc.springBoot.exception;

public class UserException extends Exception {
    UserException() {
    }

    UserException(String message) {
        super(message);
    }
}
