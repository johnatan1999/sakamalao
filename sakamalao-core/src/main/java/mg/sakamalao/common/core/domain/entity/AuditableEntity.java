package mg.sakamalao.common.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class AuditableEntity {
    protected LocalDateTime createdDate;
    protected LocalDateTime updatedDate;
    protected UUID createdByUserId;
    protected UUID updatedByUserId;
}
