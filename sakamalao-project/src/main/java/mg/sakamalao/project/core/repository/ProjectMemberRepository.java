package mg.sakamalao.project.core.repository;

import mg.sakamalao.core.domain.entity.ProjectMember;
import mg.sakamalao.project.core.domain.ProjectMemberInput;

import java.util.UUID;

public interface ProjectMemberRepository {
    ProjectMember save(ProjectMemberInput pm);
    boolean existsByProjectIdAndUserId(UUID projectId, UUID userId);
}
