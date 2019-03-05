package pl.sda.spring.mvc.springBoot.exception;

public class UserNotFoundException extends UserException {
    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
