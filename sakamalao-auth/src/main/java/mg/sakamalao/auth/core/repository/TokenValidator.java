package mg.sakamalao.auth.core.repository;

import mg.sakamalao.core.domain.entity.User;

public interface TokenValidator {
    User validate(String token);
}
