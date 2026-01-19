package mg.sakamalao.transaction.core.domain.expense;

import java.time.LocalDate;
import java.util.UUID;

public record ExpenseInput (
    String name,
    UUID categoryId,
    String description,
    double amount,
    LocalDate date,
    UUID projectId
) {
}

