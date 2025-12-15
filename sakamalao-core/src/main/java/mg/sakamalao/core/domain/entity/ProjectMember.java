package mg.sakamalao.core.domain.entity;

import mg.sakamalao.core.domain.enums.ProjectRole;

public record ProjectMember(
        String id,
        String projectId,
        String userId,
        ProjectRole role
) {
}
