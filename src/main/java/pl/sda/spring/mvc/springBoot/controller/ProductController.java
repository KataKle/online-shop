package pl.sda.spring.mvc.springBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.spring.mvc.springBoot.dto.ProductDTO;
import pl.sda.spring.mvc.springBoot.exception.ProductNotFoundException;
import pl.sda.spring.mvc.springBoot.component.Shopcart;
import pl.sda.spring.mvc.springBoot.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final Shopcart shopcart;

    @ModelAttribute
    private Shopcart getShopcart() {
        return shopcart;
    }

    @Autowired
    public ProductController(ProductService productService, Shopcart shopcart) {
        this.productService = productService;
        this.shopcart = shopcart;
    }

    @RequestMapping
    public ModelAndView getProductsView() {
        List<ProductDTO> products = productService.getProducts();
        ModelAndView mnv = new ModelAndView();
        mnv.addObject("products", products);
        mnv.setViewName("products");
        return mnv;
    }

    @RequestMapping("/{id}")
    public ModelAndView getProductDetails(@PathVariable(name = "id") long id) throws ProductNotFoundException {
        ModelAndView mnv = new ModelAndView("productDetails");
        mnv.addObject("product", productService.getProductById(id));

        return mnv;
    }
}
