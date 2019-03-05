package pl.sda.spring.mvc.springBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import java.security.Principal;
import org.springframework.stereotype.Service;

import pl.sda.spring.mvc.springBoot.dto.ModelMapper;
import pl.sda.spring.mvc.springBoot.dto.ProductDTO;
import pl.sda.spring.mvc.springBoot.dto.UserOrderDTO;
import pl.sda.spring.mvc.springBoot.exception.ProductNotFoundException;
import pl.sda.spring.mvc.springBoot.exception.UserNotFoundException;
import pl.sda.spring.mvc.springBoot.model.Product;
import pl.sda.spring.mvc.springBoot.model.UserOrder;
import pl.sda.spring.mvc.springBoot.repository.ProductRepository;
import pl.sda.spring.mvc.springBoot.repository.UserOrderRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserOrderService {
    private final UserService userService;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final UserOrderRepository userOrderRepository;

    @Autowired
    public UserOrderService(UserService userService, ProductService productService, UserOrderRepository userOrderRepository, ProductRepository productRepository) {
        this.userService = userService;
        this.productService = productService;
        this.userOrderRepository = userOrderRepository;
        this.productRepository = productRepository;
    }

    public void createNewOrder(List<ProductDTO> products, Principal principal)
            throws UserNotFoundException, ProductNotFoundException {
        UserOrder userOrder = new UserOrder();
        List<Product> productsEntity = new ArrayList<>();
        for (ProductDTO productDTO : products) {
            productsEntity.add(productRepository.findById(productDTO.getId())
                    .orElseThrow(() -> new ProductNotFoundException("Nie znaleziono produktu o id: " + productDTO.getId())));
        }
        userOrder.setUser(userService.getUserByLogin(principal.getName()));
        userOrder.setDate(LocalDate.now());
        userOrder.setProducts(productsEntity);
        userOrderRepository.save(userOrder);
    }

    public List<UserOrderDTO> getUserOrders(String userLogin) {
        return userOrderRepository.findByUser_Login(userLogin).stream().map(ModelMapper::map).collect(Collectors.toList());
    }
}
