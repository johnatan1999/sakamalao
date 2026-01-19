package mg.sakamalao.common.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Income extends AuditableEntity {
    private UUID id;
    private UUID projectId;
    private UUID importId;
    private String name;
    private String description;
    private TransactionCategory category;
    private double amount;
    private LocalDateTime date;
}
