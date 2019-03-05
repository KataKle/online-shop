package pl.sda.spring.mvc.springBoot.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String login;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<UserOrder> orders = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_role" ,joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private Set<Role> roles = new HashSet<>();
}


