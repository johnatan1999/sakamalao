package mg.sakamalao.auth.core.domain;

public record User(
    String id,
    String username,
    String email,
    String password,
    UserRoleEnum role
) {
}
