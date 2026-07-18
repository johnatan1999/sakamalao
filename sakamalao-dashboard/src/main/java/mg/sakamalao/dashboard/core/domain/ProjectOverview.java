package mg.sakamalao.dashboard.core.domain;

import java.util.UUID;

public record ProjectOverview(
        UUID id,
        String name,
        double budget
) {
}
