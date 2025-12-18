package mg.sakamalao.auth.infrastructure.driver.entity.response;

import mg.sakamalao.common.core.domain.enums.UserRoleEnum;

import java.util.UUID;

public record UserResponse (UUID id, String username, String email, UserRoleEnum role){
}
