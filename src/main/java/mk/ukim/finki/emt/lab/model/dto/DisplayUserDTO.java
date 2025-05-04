package mk.ukim.finki.emt.lab.model.dto;

import mk.ukim.finki.emt.lab.model.domain.Role;
import mk.ukim.finki.emt.lab.model.domain.User;

public record DisplayUserDTO(String username, String name, String surname, Role role) {

    public static DisplayUserDTO from(User user) {
        return new DisplayUserDTO(
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getRole()
        );
    }

    public User toUser() {
        return new User(username, name, surname, role.name());
    }
}
