package pl.sda.spring.mvc.springBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.spring.mvc.springBoot.dto.ModelMapper;
import pl.sda.spring.mvc.springBoot.dto.UserRegisterstrationDTO;
import pl.sda.spring.mvc.springBoot.exception.UserNotFoundException;
import pl.sda.spring.mvc.springBoot.model.Role;
import pl.sda.spring.mvc.springBoot.model.User;
import pl.sda.spring.mvc.springBoot.repository.RoleRepository;
import pl.sda.spring.mvc.springBoot.repository.UserRepository;

import java.util.Set;

@Service ("UserService")
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    User getUserByLogin(String login) throws UserNotFoundException {
        return userRepository
                .findByLogin(login)
                .orElseThrow(()->new UserNotFoundException("Brak użytkownika o loginie" + login));
    }

    public void registerNewUser(UserRegisterstrationDTO userRegisterstrationDTO) throws Exception {
        User user = ModelMapper.map(userRegisterstrationDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findRoleByRoleName("USER").orElseThrow(()->new Exception("Błąd przypisywania roli w bazie danych"));
        Set<Role> roles = user.getRoles();
        roles.add(role);
        userRepository.save(user);
    }

    public boolean userAlreadyExist(String login){
        return userRepository.findByLogin(login).isPresent();
    }
}
