package pl.sda.spring.mvc.springBoot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserOrder {
    @Id
    @GeneratedValue
    private long id;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToMany
    @JoinTable(name="product_order", joinColumns = {@JoinColumn(name="id_userorder")}, inverseJoinColumns =
            {@JoinColumn (name="id_product")})
    List<Product> products = new ArrayList<>();


}
