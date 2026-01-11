package mg.sakamalao.expense.core.domain;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateExpenseInput(
        UUID expenseId,
        String name,
        String description,
        double amount,
        UUID categoryId,
        UUID projectId,
        LocalDate date
) {}

