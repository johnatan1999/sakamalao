package mg.sakamalao.common.core.domain.entity;

import mg.sakamalao.common.core.domain.enums.UserRoleEnum;

import java.util.UUID;

public record User(
    UUID id,
    String username,
    String email,
    String password,
    UserRoleEnum role
) {
}
