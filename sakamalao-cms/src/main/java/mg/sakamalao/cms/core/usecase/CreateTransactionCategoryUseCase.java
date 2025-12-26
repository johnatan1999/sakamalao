package mg.sakamalao.cms.core.usecase;

import mg.sakamalao.cms.core.domain.TransactionCategory;
import mg.sakamalao.cms.core.domain.input.TransactionCategoryInput;
import mg.sakamalao.cms.core.repository.TransactionCategoryRepository;
import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.core.domain.exception.ResourceAlreadyExistsException;
import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.common.validator.FieldValidator;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateTransactionCategoryUseCase {
    private final TransactionCategoryRepository repository;
    private final ProjectAccessPort projectAccessPort;

    public CreateTransactionCategoryUseCase(
            TransactionCategoryRepository repository,
            ProjectAccessPort projectAccessPort
    ) {
        this.repository = repository;
        this.projectAccessPort = projectAccessPort;
    }

    public TransactionCategory create(UUID userId, UUID projectId, TransactionCategoryInput input) {
        FieldValidator.required("name", input.name());
        FieldValidator.notNull("type", input.type());
        FieldValidator.notNull("projectId", projectId);

        var hasAccess = projectAccessPort.hasAccess(userId, projectId);
        if (!hasAccess) {
            throw new EntityNotFoundException("Project with id=%s not found".formatted(projectId));
        }

        var category = repository.findByProjectIdAndName(projectId, input.name());
        if (category.isPresent()) {
            throw new ResourceAlreadyExistsException("Category with name=%s already exists for this project");
        }

        return repository.save(new TransactionCategory(
                null,
                projectId,
                input.name(),
                input.type(),
                LocalDateTime.now()
        ));
    }
}
