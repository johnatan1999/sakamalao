package mg.sakamalao.common.core.domain.entity;

import mg.sakamalao.common.core.domain.enums.ProjectRole;

public record ProjectMember(
        String id,
        String projectId,
        String userId,
        ProjectRole role
) {
}
