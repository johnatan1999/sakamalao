package mg.sakamalao.transaction.core.usecase.income;

import mg.sakamalao.common.core.domain.entity.Income;
import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.transaction.core.repository.income.IncomeRepository;

import java.util.List;
import java.util.UUID;

public class FindIncomeUseCase {
    private final IncomeRepository repository;
    private final ProjectAccessPort projectAccess;

    public FindIncomeUseCase(IncomeRepository repository, ProjectAccessPort projectAccess) {
        this.repository = repository;
        this.projectAccess = projectAccess;
    }

    public List<Income> findByProject(UUID projectId, UUID userId) {
        var hasAccess = projectAccess.hasAccess(userId, projectId);
        // Throw a not found exception to avoid telling
        // the user that the project exists but that they cannot access it
        if (!hasAccess) {
            throw new EntityNotFoundException("Project with id=%s not found".formatted(projectId));
        }
        return repository.findByProjectId(projectId);
    }
}
