package pl.sda.spring.mvc.springBoot.exception;

public class ProductException extends Exception {
    ProductException() {
    }

    ProductException(String message) {
        super(message);
    }
}
