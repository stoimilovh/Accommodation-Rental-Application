package mk.ukim.finki.emt.lab.model.dto;

import mk.ukim.finki.emt.lab.model.domain.Role;
import mk.ukim.finki.emt.lab.model.domain.User;


public record CreateUserDTO (String username, String password, String repeatPassword, String name, String surname, Role role) {

    public User toUser() {
        return new User(username, password, name, surname, role);
    }

}
