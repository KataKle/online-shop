package pl.sda.spring.mvc.springBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.sda.spring.mvc.springBoot.dto.ModelMapper;
import pl.sda.spring.mvc.springBoot.dto.ProductDTO;
import pl.sda.spring.mvc.springBoot.exception.ProductNotFoundException;
import pl.sda.spring.mvc.springBoot.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getProducts() {
        return productRepository.findAll().stream()
                .map(ModelMapper::map)
                .collect(Collectors.toList());
    }

    public void addNewProduct(@ModelAttribute("Product") ProductDTO productDto) {
        productRepository.save(ModelMapper.map(productDto));
    }

    public ProductDTO getProductById(long id) throws ProductNotFoundException {
        return productRepository.findById(id)
                .map(ModelMapper::map)
                .orElseThrow(() -> new ProductNotFoundException("Nie znaleziono produktu o id: " + id));
    }

    public List<ProductDTO> getProductsByIds(List<Long> ids) {
        return productRepository.findAllById(ids).stream()
                .map(ModelMapper::map)
                .collect(Collectors.toList());
    }
}
