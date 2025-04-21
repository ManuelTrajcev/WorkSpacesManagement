package mk.ukim.finki.wp.workspaces.service.application;

import mk.ukim.finki.wp.workspaces.model.dto.CreateUserDto;
import mk.ukim.finki.wp.workspaces.model.dto.DisplayUserDto;
import mk.ukim.finki.wp.workspaces.model.dto.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {
    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<DisplayUserDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);
}
