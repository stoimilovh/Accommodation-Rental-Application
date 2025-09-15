package mk.ukim.finki.emt.lab.service.domain;


import mk.ukim.finki.emt.lab.model.domain.Role;
import mk.ukim.finki.emt.lab.model.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

    User login(String username, String password);

    User findByUsername(String username);
}
