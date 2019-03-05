package pl.sda.spring.mvc.springBoot.exception;

public class ProductNotFoundException extends ProductException {
    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
