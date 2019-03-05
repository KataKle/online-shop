package pl.sda.spring.mvc.springBoot.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.sda.spring.mvc.springBoot.dto.UserRegisterstrationDTO;
import pl.sda.spring.mvc.springBoot.service.UserService;

@Component("userRegistrationValidator")
public class UserRegistrationValidator implements Validator {
    UserService userService;

    @Autowired
    public UserRegistrationValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserRegisterstrationDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserRegisterstrationDTO userRegDTO = (UserRegisterstrationDTO) o;
        if (!userService.userAlreadyExist(userRegDTO.getLogin())) {
            ValidationUtils.rejectIfEmpty(errors, "login", "userRegDTO.validator.field.notEmpty");
            ValidationUtils.rejectIfEmpty(errors, "password", "userRegDTO.validator.field.notEmpty");
            ValidationUtils.rejectIfEmpty(errors, "repeatedPassword", "userRegDTO.validator.field.notEmpty");
        } else {
            errors.rejectValue("login", "userRegDTO.validator.field.login.existingUser");
        }
        if (!userRegDTO.getPassword().equals(userRegDTO.getRepeatedPassword())) {
            errors.rejectValue("repeatedPassword", "userRegDTO.validator.field.repeatedPassword.passwordNotTheSame");
        }
    }
}
