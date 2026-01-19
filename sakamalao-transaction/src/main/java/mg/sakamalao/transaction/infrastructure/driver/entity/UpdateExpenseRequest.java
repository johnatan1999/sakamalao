package mg.sakamalao.transaction.infrastructure.driver.entity;

import mg.sakamalao.common.core.domain.enums.TransactionType;

import java.time.LocalDateTime;
import java.util.UUID;

public record UpdateExpenseRequest(
        String name,
        String description,
        double amount,
        UUID categoryId,
        TransactionType type,
        UUID projectId,
        LocalDateTime date
) {
}
