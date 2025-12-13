package mg.sakamalao.core.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import mg.sakamalao.core.domain.enums.EntityStatus;

@Data
@NoArgsConstructor
public abstract class BaseEntity {
    private String id;
    private EntityStatus status;
}
