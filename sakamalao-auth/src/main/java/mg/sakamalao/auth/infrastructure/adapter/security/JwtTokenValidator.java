package mg.sakamalao.auth.infrastructure.adapter.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import mg.sakamalao.auth.core.repository.TokenValidator;
import mg.sakamalao.auth.infrastructure.config.JwtConfig;
import mg.sakamalao.core.domain.entity.User;
import mg.sakamalao.core.domain.enums.UserRoleEnum;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.UUID;

@Component
public class JwtTokenValidator implements TokenValidator {

    private final Key signingKey;

    public JwtTokenValidator(JwtConfig jwtConfig) {
        this.signingKey = Keys.hmacShaKeyFor(
                jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8)
        );
    }

    @Override
    public User validate(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        String role = claims.get("role", String.class);
        return new User(
                UUID.fromString(claims.getSubject()),
                claims.get("username", String.class),
                claims.get("email", String.class),
                null, // password
                UserRoleEnum.valueOf(role)
        );
    }
}
