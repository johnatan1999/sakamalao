package mg.sakamalao.core.domain.entity;

import mg.sakamalao.core.domain.enums.UserRoleEnum;

import java.util.UUID;

public record User(
    UUID id,
    String username,
    String email,
    String password,
    UserRoleEnum role
) {
}
