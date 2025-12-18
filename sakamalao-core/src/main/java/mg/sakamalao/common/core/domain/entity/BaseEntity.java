package mg.sakamalao.common.core.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import mg.sakamalao.common.core.domain.enums.EntityStatus;

import java.util.UUID;

@Data
@NoArgsConstructor
public abstract class BaseEntity {
    private UUID id;
    private EntityStatus status;
}
