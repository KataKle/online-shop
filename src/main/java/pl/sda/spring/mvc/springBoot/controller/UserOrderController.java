package pl.sda.spring.mvc.springBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.spring.mvc.springBoot.dto.UserOrderDTO;
import pl.sda.spring.mvc.springBoot.service.UserOrderService;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/orders")
public class UserOrderController {
    private UserOrderService userOrderService;

    @Autowired
    public UserOrderController(UserOrderService userOrderService) {
        this.userOrderService = userOrderService;
    }

    @RequestMapping
    public ModelAndView getOrdersView(Principal principal) {
        ModelAndView mnv = new ModelAndView("orders");
        List<UserOrderDTO> orders = userOrderService.getUserOrders(principal.getName());
        mnv.addObject("orders", orders);
        return mnv;
    }
}
