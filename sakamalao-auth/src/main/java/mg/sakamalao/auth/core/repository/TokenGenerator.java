package mg.sakamalao.auth.core.repository;

import mg.sakamalao.auth.core.domain.User;

public interface TokenGenerator {
    String generate(User user);
}
