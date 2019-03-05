package pl.sda.spring.mvc.springBoot.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.spring.mvc.springBoot.exception.ProductException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = ProductException.class)
    public ModelAndView productException(Exception ex) {
        ex.printStackTrace();
        ModelAndView mnv = new ModelAndView("errorView");
        mnv.addObject("errorMessage", ex.getMessage());
        return mnv;
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView basicException(Exception ex) {
        ex.printStackTrace();
        ModelAndView error = new ModelAndView("errorView");
        error.addObject("errorMessage", ex.getClass() + " " + ex.getMessage());
        return error;
    }
}
