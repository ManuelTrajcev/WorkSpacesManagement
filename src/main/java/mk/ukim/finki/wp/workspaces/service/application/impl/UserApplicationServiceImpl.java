package mk.ukim.finki.wp.workspaces.service.application.impl;

import mk.ukim.finki.wp.workspaces.dto.CreateUserDto;
import mk.ukim.finki.wp.workspaces.dto.DisplayUserDto;
import mk.ukim.finki.wp.workspaces.dto.LoginUserDto;
import mk.ukim.finki.wp.workspaces.model.domain.User;
import mk.ukim.finki.wp.workspaces.repository.UserWorkspaceRepository;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.workspaces.service.application.UserApplicationService;
import mk.ukim.finki.wp.workspaces.service.domain.UserService;
import mk.ukim.finki.wp.workspaces.service.domain.UserWorkspaceService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {
    private final UserService userService;
    private final UserWorkspaceRepository userWorkspaceRepository;
    private final UserWorkspaceService userWorkspaceService;
    private final AuthenticationManager authenticationManager;


    public UserApplicationServiceImpl(UserService userService, UserWorkspaceRepository userWorkspaceRepository, UserWorkspaceService userWorkspaceService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.userWorkspaceRepository = userWorkspaceRepository;
        this.userWorkspaceService = userWorkspaceService;
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                createUserDto.repeatPassword()
        );
        return Optional.of(DisplayUserDto.from(user));
    }

    @Override
    public Optional<DisplayUserDto> login(LoginUserDto loginUserDto) {
        User user = userService.login(
                loginUserDto.username(),
                loginUserDto.password()
        );

        //Map<Long, Role> claims =userWorkspaceService.workspacesWithRolesForUser(user.getId());
        //String token = jwtHelper.generateTokenWithWorkspacesAccess(user, claims);       //workspaceId, Role
        return Optional.of(DisplayUserDto.from(userService.login(
                loginUserDto.username(),
                loginUserDto.password()
        )));
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(userService.findByUsername(username)));
    }

    @Override
    public DisplayUserDto getDisplayUserDto(String username) {
        return DisplayUserDto.from(userService.findByUsername(username));
    }
}
