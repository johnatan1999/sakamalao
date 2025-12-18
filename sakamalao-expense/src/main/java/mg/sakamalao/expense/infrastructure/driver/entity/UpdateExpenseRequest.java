package mg.sakamalao.expense.infrastructure.driver.entity;

import mg.sakamalao.common.core.domain.enums.ExpenseCategory;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateExpenseRequest(
        String name,
        String description,
        double amount,
        ExpenseCategory category,
        UUID projectId,
        LocalDate date
) {
}
