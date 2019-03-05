package pl.sda.spring.mvc.springBoot.dto;

import lombok.*;
import pl.sda.spring.mvc.springBoot.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserOrderDTO {
    private long id;
    private LocalDate date;
    private User user;
    private List<ProductDTO> products = new ArrayList<>();
}
