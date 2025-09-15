package mk.ukim.finki.emt.lab.service.application.impl;

import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.model.dto.CreateUserDTO;
import mk.ukim.finki.emt.lab.model.dto.DisplayUserDTO;
import mk.ukim.finki.emt.lab.model.dto.LoginUserDTO;
import mk.ukim.finki.emt.lab.service.application.UserApplicationService;
import mk.ukim.finki.emt.lab.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;

    public UserApplicationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<DisplayUserDTO> register(CreateUserDTO createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role()
        );
        return Optional.of(DisplayUserDTO.from(user));
    }

    @Override
    public Optional<DisplayUserDTO> login(LoginUserDTO loginUserDto) {
        return Optional.of(DisplayUserDTO.from(userService.login(
                loginUserDto.username(),
                loginUserDto.password()
        )));
    }

    @Override
    public Optional<DisplayUserDTO> findByUsername(String username) {
        return Optional.of(DisplayUserDTO.from(userService.findByUsername(username)));
    }
}
