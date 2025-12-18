package mg.sakamalao.auth.core.repository;

import mg.sakamalao.common.core.domain.entity.User;

public interface TokenGenerator {
    String generate(User user);
}
