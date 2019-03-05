package pl.sda.spring.mvc.springBoot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sda.spring.mvc.springBoot.model.Role;
import pl.sda.spring.mvc.springBoot.model.User;
import pl.sda.spring.mvc.springBoot.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("userServiceDetailsImpl")
public class UserServiceDetailsImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceDetailsImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Optional<User> byLogin = userRepository.findByLogin(login);

        if (!byLogin.isPresent()) {
            throw new UsernameNotFoundException("Can't find user with login: " + login);
        }
        return new org.springframework.security.core.userdetails.User(
                byLogin.get().getLogin(), byLogin.get().getPassword(), getGrantedAuthorities(byLogin.get()));
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        System.out.print("authorities :" + authorities);
        return authorities;
    }
}
