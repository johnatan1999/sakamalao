package mg.sakamalao.common.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import mg.sakamalao.common.core.domain.enums.ExpenseCategory;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Expense extends AuditableEntity {
    private UUID id;
    private UUID projectId;
    private String name;
    private String description;
    private ExpenseCategory category;
    private double amount;
    private LocalDate date;
}
