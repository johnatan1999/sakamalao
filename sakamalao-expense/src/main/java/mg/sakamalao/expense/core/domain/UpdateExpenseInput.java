package mg.sakamalao.expense.core.domain;

import mg.sakamalao.common.core.domain.enums.ExpenseCategory;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateExpenseInput(
        UUID expenseId,
        String name,
        String description,
        double amount,
        ExpenseCategory category,
        UUID projectId,
        LocalDate date
) {}

