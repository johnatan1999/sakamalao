package mg.sakamalao.project.core.usecase;

import mg.sakamalao.common.core.domain.entity.Project;
import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.core.domain.input.ProjectInput;
import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.common.validator.FieldValidator;
import mg.sakamalao.project.core.repository.ProjectRepository;

import java.util.UUID;

public class UpdateProjectUseCase {
    private final ProjectRepository repository;
    private final ProjectAccessPort projectAccessPort;

    public UpdateProjectUseCase(ProjectRepository repository, ProjectAccessPort projectAccessPort) {
        this.repository = repository;
        this.projectAccessPort = projectAccessPort;
    }

    public Project update(UUID projectId, ProjectInput input, UUID ownerId) {
        FieldValidator.notNull("project", input);
        FieldValidator.notNull("Owner id", ownerId);
        FieldValidator.required("name", input.name());
        FieldValidator.notNull("projectId", projectId);

        boolean hasAccess = projectAccessPort.hasAccess(ownerId, projectId);
        if (!hasAccess) {
            throw new EntityNotFoundException("Project with id=%s not found".formatted(projectId));
        }

        var p = repository.findById(projectId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Project with id=%s not found".formatted(projectId))
                );

        p.setBudget(input.budget());
        p.setDescription(input.description());
        p.setName(input.name());
        if (input.startDate() != null) {
            p.setStartDate(input.startDate());
        }

        return repository.save(p);
    }
}
