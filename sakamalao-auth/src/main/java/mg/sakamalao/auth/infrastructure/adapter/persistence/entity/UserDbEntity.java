package mg.sakamalao.auth.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.sakamalao.auth.core.domain.UserRoleEnum;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserDbEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;
}
