package mg.sakamalao.expense.infrastructure.driver.entity;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateExpenseRequest(
        String name,
        String description,
        double amount,
        UUID categoryId,
        UUID projectId,
        LocalDate date
) {
}
