package pl.sda.spring.mvc.springBoot.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductDTO {
    private long id;
    private String name;
    private String description;
    private BigDecimal price;

    public ProductDTO(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
