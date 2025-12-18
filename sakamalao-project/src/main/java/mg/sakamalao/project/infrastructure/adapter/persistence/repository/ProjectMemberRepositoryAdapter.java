package mg.sakamalao.project.infrastructure.adapter.persistence.repository;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.core.domain.entity.ProjectMember;
import mg.sakamalao.project.core.domain.ProjectMemberInput;
import mg.sakamalao.project.core.repository.ProjectMemberRepository;
import mg.sakamalao.project.infrastructure.adapter.persistence.entity.ProjectMemberDbEntity;
import mg.sakamalao.project.infrastructure.adapter.persistence.jpa.ProjectMemberJpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProjectMemberRepositoryAdapter implements ProjectMemberRepository {

    private final ProjectMemberJpaRepository repository;

    @Override
    public ProjectMember save(ProjectMemberInput pm) {
        var newPm = repository.save(new ProjectMemberDbEntity(
                null,
                pm.projectId(),
                pm.userId(),
                pm.role()
        ));
        return new ProjectMember(
                newPm.getId().toString(),
                newPm.getProjectId().toString(),
                newPm.getUserId().toString(),
                newPm.getRole()
        );
    }

    @Override
    public boolean existsByProjectIdAndUserId(UUID projectId, UUID userId) {
        return false;
    }
}
