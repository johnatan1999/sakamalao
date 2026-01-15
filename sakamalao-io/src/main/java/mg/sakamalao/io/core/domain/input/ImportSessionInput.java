package mg.sakamalao.io.core.domain.input;

import java.time.LocalDateTime;
import java.util.UUID;

public record ImportSessionInput(
        UUID userId,
        UUID projectId,
        LocalDateTime createdDate
) {
}
