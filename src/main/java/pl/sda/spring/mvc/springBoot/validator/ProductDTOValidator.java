package pl.sda.spring.mvc.springBoot.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.sda.spring.mvc.springBoot.dto.ProductDTO;

@Component("productDTOValidator")
public class ProductDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ProductDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ProductDTO product = (ProductDTO) o;
        ValidationUtils.rejectIfEmpty(errors, "name", "product.validator.field.notEmpty");
        ValidationUtils.rejectIfEmpty(errors, "description", "product.validator.field.notEmpty");
        ValidationUtils.rejectIfEmpty(errors, "price", "product.validator.field.notEmpty");

        if (product.getPrice() == null || product.getPrice().intValue() <= 0) {
            errors.rejectValue("price", "product.validator.field.price.fieldGreaterThanZero");
        }

    }
}
