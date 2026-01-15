package mg.sakamalao.io.core.domain;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record ImportSession(
        UUID id,
        UUID projectId,
        UUID userId,
        LocalDateTime createdAt
) {
}
