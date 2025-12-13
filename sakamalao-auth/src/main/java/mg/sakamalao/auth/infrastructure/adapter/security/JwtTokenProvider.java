package mg.sakamalao.auth.infrastructure.adapter.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import mg.sakamalao.auth.core.domain.User;
import mg.sakamalao.auth.core.repository.TokenGenerator;
import mg.sakamalao.auth.infrastructure.config.JwtConfig;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider implements TokenGenerator {

    private final JwtConfig jwtConfig;
    private final Key signingKey;

    public JwtTokenProvider(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
        this.signingKey = Keys.hmacShaKeyFor(
                jwtConfig.getSecret().getBytes(StandardCharsets.UTF_8)
        );
    }

    @Override
    public String generate(User user) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + jwtConfig.getExpiration());
        return Jwts.builder()
                .setSubject(user.id())
                .claim("username", user.username())
                .claim("role", user.role())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }
}
