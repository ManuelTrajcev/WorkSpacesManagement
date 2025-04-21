package mk.ukim.finki.wp.workspaces.model.dto;

public record CreateUserDto(
        String username,
        String email,
        String password,
        String repeatPassword
) {
}
