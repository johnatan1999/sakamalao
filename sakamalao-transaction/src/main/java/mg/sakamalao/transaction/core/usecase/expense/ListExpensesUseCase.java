package mg.sakamalao.transaction.core.usecase.expense;

import mg.sakamalao.common.core.domain.entity.Expense;
import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.transaction.core.repository.expense.ExpenseRepository;

import java.util.List;
import java.util.UUID;

public class ListExpensesUseCase {
    private final ExpenseRepository repository;
    private final ProjectAccessPort projectAccessPort;

    public ListExpensesUseCase(ExpenseRepository repository, ProjectAccessPort projectAccessPort) {
        this.repository = repository;
        this.projectAccessPort = projectAccessPort;
    }

    public List<Expense> findByProject(UUID projectId, UUID userId) {
        var hasAccess = projectAccessPort.hasAccess(userId, projectId);
        // Throw a not found exception to avoid telling
        // the user that the project exists but that they cannot access it
        if (!hasAccess) {
            throw new EntityNotFoundException("Project with id=%s not found".formatted(projectId));
        }
        return repository.findByProjectId(projectId);
    }
}
