package mg.sakamalao.transaction.core.domain.expense;

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

