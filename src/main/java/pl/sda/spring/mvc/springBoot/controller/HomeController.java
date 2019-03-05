package pl.sda.spring.mvc.springBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.spring.mvc.springBoot.dto.UserRegisterstrationDTO;
import pl.sda.spring.mvc.springBoot.service.UserService;

@Controller
@RequestMapping("/")
public class HomeController {
    private final Validator userRegistrationValidator;
    private final UserService userService;

    @InitBinder("regForm")
    public void initBinding(WebDataBinder binder) {
        binder.setValidator(userRegistrationValidator);
    }

    @Autowired
    public HomeController(@Qualifier("userRegistrationValidator") Validator userRegistrationValidator,
                          @Qualifier("UserService") UserService userService) {
        this.userRegistrationValidator = userRegistrationValidator;
        this.userService = userService;
    }

    @RequestMapping
    public ModelAndView getIndex() {
        ModelAndView mnv = new ModelAndView();
        mnv.setViewName("index");
        return mnv;
    }

    @RequestMapping("/registration")
    public ModelAndView getRegistration() {
        ModelAndView mnv = new ModelAndView();
        mnv.addObject("regForm", new UserRegisterstrationDTO());
        mnv.setViewName("registration");
        return mnv;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("regForm") @Validated UserRegisterstrationDTO regForm, BindingResult bindingResult) {
        System.out.println(regForm);
        if (bindingResult.hasErrors()) {
            return "/registration";
        }
        try {
            userService.registerNewUser(regForm);
        } catch (Exception e) {
            e.getMessage();
        }
        return "redirect:/";
    }

    @RequestMapping("/login")
    public ModelAndView getLoggingIn(){
        ModelAndView mnv = new ModelAndView();
        mnv.setViewName("login");
        return mnv;
    }
}
