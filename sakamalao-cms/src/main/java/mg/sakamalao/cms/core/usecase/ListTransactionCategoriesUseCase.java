package mg.sakamalao.cms.core.usecase;

import mg.sakamalao.cms.core.repository.TransactionCategoryRepository;
import mg.sakamalao.common.core.domain.entity.TransactionCategory;
import mg.sakamalao.common.core.domain.enums.TransactionType;
import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.core.port.ProjectAccessPort;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ListTransactionCategoriesUseCase {
    private final TransactionCategoryRepository repository;
    private final ProjectAccessPort projectAccessPort;

    public ListTransactionCategoriesUseCase(TransactionCategoryRepository repository, ProjectAccessPort projectAccessPort) {
        this.repository = repository;
        this.projectAccessPort = projectAccessPort;
    }

    public Map<TransactionType, List<TransactionCategory>> findByProject(UUID projectId, UUID userId) {
        var hasAccess = projectAccessPort.hasAccess(userId, projectId);
        // Throw a not found exception to avoid telling
        // the user that the project exists but that they cannot access it
        if (!hasAccess) {
            throw new EntityNotFoundException("Project with id=%s not found".formatted(projectId));
        }
        return repository.getTransactionCategories(projectId);
    }
}
