package pl.sda.spring.mvc.springBoot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.spring.mvc.springBoot.dto.ProductDTO;
import pl.sda.spring.mvc.springBoot.service.ProductService;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    private ProductService productService;
    private final Validator productDTOValidator;

    @InitBinder("product")
    public void initBinding(WebDataBinder binder) {
        binder.setValidator(productDTOValidator);
    }

    @Autowired
    public AdminController(ProductService productService, @Qualifier("productDTOValidator") Validator productDTOValidator) {
        this.productService = productService;
        this.productDTOValidator = productDTOValidator;
    }

    @RequestMapping
    public ModelAndView getIndex() {
        ModelAndView mnv = new ModelAndView("admin/index");
        mnv.addObject("product", new ProductDTO());
        return mnv;
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String addProduct(@ModelAttribute("product") @Validated ProductDTO product, BindingResult bindingResult) {
        System.out.println(product);
        if (bindingResult.hasErrors()) {
            return "admin/index";
        }
        productService.addNewProduct(product);
        return "redirect:/products";
    }
}
