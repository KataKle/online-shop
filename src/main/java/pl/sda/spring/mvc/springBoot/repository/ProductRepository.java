package pl.sda.spring.mvc.springBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.spring.mvc.springBoot.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllById(List<Long> ids);

}
