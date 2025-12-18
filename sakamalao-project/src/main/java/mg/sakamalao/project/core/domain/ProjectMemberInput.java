package mg.sakamalao.project.core.domain;

import mg.sakamalao.common.core.domain.enums.ProjectRole;

import java.util.UUID;

public record ProjectMemberInput(
        UUID projectId,
        UUID userId,
        ProjectRole role
) {
}
