package mg.sakamalao.transaction.infrastructure.driver.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public record UpdateExpenseRequest(
        String name,
        String description,
        double amount,
        UUID categoryId,
        UUID projectId,
        LocalDateTime date
) {
}
