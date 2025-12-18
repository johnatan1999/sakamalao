package mg.sakamalao.common.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.sakamalao.common.core.domain.enums.ExpenseCategory;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expense {
    private UUID id;
    private String name;
    private String description;
    private ExpenseCategory category;
    private double amount;
    private LocalDate date;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private UUID projectId;
    private UUID createdByUserId;
    private UUID updatedByUserId;
}
