package mg.sakamalao.cms.core.usecase;

import mg.sakamalao.cms.core.repository.TransactionCategoryRepository;
import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.common.validator.FieldValidator;

import java.util.UUID;

public class DeleteTransactionCategoryUseCase {
    private final TransactionCategoryRepository repository;
    private final ProjectAccessPort projectAccessPort;

    public DeleteTransactionCategoryUseCase(TransactionCategoryRepository repository, ProjectAccessPort projectAccessPort) {
        this.repository = repository;
        this.projectAccessPort = projectAccessPort;
    }

    public void delete(UUID userId, UUID projectId, UUID categoryId) {
        FieldValidator.notNull("projectId", projectId);
        FieldValidator.notNull("categoryId", categoryId);

        var hasAccess = projectAccessPort.hasAccess(userId, projectId);
        if (!hasAccess) {
            throw new EntityNotFoundException("Project with id=%s not found".formatted(projectId));
        }

        repository.findById(categoryId).orElseThrow(() ->
                new EntityNotFoundException("Category with id=%s not found".formatted(categoryId))
        );

        repository.deleteById(categoryId);
    }
}
