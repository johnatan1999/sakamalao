package mg.sakamalao.common.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import mg.sakamalao.common.core.domain.enums.IncomeCategory;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Income extends AuditableEntity {
    private UUID id;
    private UUID projectId;
    private String name;
    private String description;
    private IncomeCategory category;
    private double amount;
    private LocalDate date;
}
