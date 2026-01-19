package mg.sakamalao.transaction.core.domain.input;

import lombok.Builder;
import mg.sakamalao.common.core.domain.entity.TransactionCategory;
import mg.sakamalao.common.core.domain.enums.TransactionType;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record UpdateTransactionInput(
   UUID projectId,
   String name,
   String description,
   TransactionType type,
   TransactionCategory category,
   double amount,
   LocalDateTime date
) {
}
