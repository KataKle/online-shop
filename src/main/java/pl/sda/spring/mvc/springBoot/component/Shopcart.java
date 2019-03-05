package pl.sda.spring.mvc.springBoot.component;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import pl.sda.spring.mvc.springBoot.dto.ProductDTO;
import pl.sda.spring.mvc.springBoot.exception.ProductNotFoundException;
import pl.sda.spring.mvc.springBoot.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
@ToString
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Shopcart {
    private List<ProductDTO> products = new ArrayList<>();
    private ProductService productService;

    @Autowired
    public Shopcart(ProductService productService) {
        this.productService = productService;
    }

    public void addProductById(Long id) throws ProductNotFoundException {
        ProductDTO productById = productService.getProductById(id);
        products.add(productById);
    }
}
