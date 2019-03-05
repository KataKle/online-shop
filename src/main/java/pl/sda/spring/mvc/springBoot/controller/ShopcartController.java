package pl.sda.spring.mvc.springBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.spring.mvc.springBoot.exception.ProductNotFoundException;
import pl.sda.spring.mvc.springBoot.component.Shopcart;
import pl.sda.spring.mvc.springBoot.exception.UserNotFoundException;
import pl.sda.spring.mvc.springBoot.service.UserOrderService;

import java.security.Principal;

@Controller
@RequestMapping("/shopcart")
public class ShopcartController {
    private final Shopcart shopcart;
    private final UserOrderService userOrderService;

    @Autowired
    public ShopcartController(Shopcart shopcart, UserOrderService userOrderService) {
        this.shopcart = shopcart;
        this.userOrderService = userOrderService;
    }

    @ModelAttribute("shopcart")
    private Shopcart getShopcart() {
        return shopcart;
    }

    @GetMapping
    public ModelAndView displayShopcart() {
        ModelAndView mnv = new ModelAndView("shopcart");
        mnv.setViewName("shopcart");
        return mnv;
    }

    @GetMapping("/addproduct")
    public String addToShopcart(@RequestParam(name = "idproduct") String idProduct) throws ProductNotFoundException {
        long productId = Long.valueOf(idProduct);
        shopcart.addProductById(productId);
        return "redirect:/products";
    }

    @GetMapping("/placeorder")
    public String placeOrder(Principal principal) throws ProductNotFoundException, UserNotFoundException {
        userOrderService.createNewOrder(shopcart.getProducts(), principal);
        shopcart.getProducts().clear();
        return "redirect:/orders";
    }
}
