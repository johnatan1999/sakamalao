package mg.sakamalao.project.core.usecase.member;

import mg.sakamalao.core.domain.enums.ProjectRole;
import mg.sakamalao.core.domain.exception.ProjectException;
import mg.sakamalao.core.validator.FieldValidator;
import mg.sakamalao.project.core.domain.ProjectMemberInput;
import mg.sakamalao.project.core.repository.ProjectMemberRepository;

import java.util.UUID;

public class AddProjectMemberUseCase {

    private final ProjectMemberRepository repository;

    public AddProjectMemberUseCase(ProjectMemberRepository repository) {
        this.repository = repository;
    }

    public void addMember(UUID projectId, UUID userId, ProjectRole role) {
        FieldValidator.notNull("projectId", projectId);
        FieldValidator.notNull("userId", userId);
        FieldValidator.notNull("role", role);

        if (repository.existsByProjectIdAndUserId(projectId, userId)) {
            throw new ProjectException("User already member of project");
        }

        repository.save(new ProjectMemberInput(
                projectId,
                userId,
                role
        ));
    }

}
