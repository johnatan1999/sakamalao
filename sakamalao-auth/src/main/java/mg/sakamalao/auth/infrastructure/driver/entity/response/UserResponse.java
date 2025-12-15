package mg.sakamalao.auth.infrastructure.driver.entity.response;

import mg.sakamalao.auth.core.domain.UserRoleEnum;

public record UserResponse (String id, String username, String email, UserRoleEnum role){
}
