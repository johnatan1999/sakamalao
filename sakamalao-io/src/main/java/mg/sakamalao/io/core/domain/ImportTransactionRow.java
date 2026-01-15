package mg.sakamalao.io.core.domain;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ImportTransactionRow(
        UUID id,
        UUID sessionId,
        String name,
        String type,
        String category,
        double amount
) {}
