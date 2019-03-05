package pl.sda.spring.mvc.springBoot.dto;

import pl.sda.spring.mvc.springBoot.model.Product;
import pl.sda.spring.mvc.springBoot.model.User;
import pl.sda.spring.mvc.springBoot.model.UserOrder;

import java.util.HashSet;
import java.util.stream.Collectors;

public class ModelMapper {
    private ModelMapper() {
    }

    public static ProductDTO map(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public static Product map(ProductDTO productDTO) {
        return new Product(productDTO.getId(), productDTO.getName(), productDTO.getDescription(), productDTO.getPrice());
    }

    public static UserOrder map(UserOrderDTO userOrderDTO) {
        return UserOrder.builder()
                .id(userOrderDTO.getId())
                .date(userOrderDTO.getDate())
                .user(userOrderDTO.getUser())
                .products(userOrderDTO.getProducts().stream().map(ModelMapper::map).collect(Collectors.toList()))
                .build();
    }

    public static UserOrderDTO map(UserOrder userOrder) {
        return UserOrderDTO.builder()
                .id(userOrder.getId())
                .date(userOrder.getDate())
                .user(userOrder.getUser())
                .products(userOrder.getProducts().stream().map(ModelMapper::map).collect(Collectors.toList()))
                .build();
    }

    public static User map(UserRegisterstrationDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .login(userDTO.getLogin())
                .password(userDTO.getPassword())
                .roles(new HashSet<>())
                .build();
    }

    public static UserRegisterstrationDTO map(User user) {
        return UserRegisterstrationDTO.builder()
                .id(user.getId())
                .login(user.getLogin())
                .password(user.getPassword())
                .build();
    }
}
