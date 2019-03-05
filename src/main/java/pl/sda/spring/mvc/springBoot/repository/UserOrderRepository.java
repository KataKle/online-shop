package pl.sda.spring.mvc.springBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.spring.mvc.springBoot.model.User;
import pl.sda.spring.mvc.springBoot.model.UserOrder;

import java.util.List;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {
    List<UserOrder> findByUser_Login(String login);
}
