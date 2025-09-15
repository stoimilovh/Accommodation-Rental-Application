package mk.ukim.finki.emt.lab.service.application;

import mk.ukim.finki.emt.lab.model.dto.CreateUserDTO;
import mk.ukim.finki.emt.lab.model.dto.DisplayUserDTO;
import mk.ukim.finki.emt.lab.model.dto.LoginUserDTO;

import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDTO> register(CreateUserDTO createUserDto);

    Optional<DisplayUserDTO> login(LoginUserDTO loginUserDto);

    Optional<DisplayUserDTO> findByUsername(String username);
}
