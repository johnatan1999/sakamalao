package mg.sakamalao.io.core.domain.input;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record ImportTransactionRowInput(
        UUID sessionId,
        String name,
        String description,
        String type,
        String category,
        double amount,
        LocalDateTime createdDate
) {
}
