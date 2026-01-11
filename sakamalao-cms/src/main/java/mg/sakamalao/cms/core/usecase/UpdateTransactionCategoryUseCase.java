package mg.sakamalao.cms.core.usecase;

import mg.sakamalao.cms.core.domain.input.TransactionCategoryInput;
import mg.sakamalao.cms.core.repository.TransactionCategoryRepository;
import mg.sakamalao.common.core.domain.entity.TransactionCategory;
import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.core.domain.exception.ResourceAlreadyExistsException;
import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.common.validator.FieldValidator;

import java.util.UUID;

public class UpdateTransactionCategoryUseCase {

    private final TransactionCategoryRepository repository;
    private final ProjectAccessPort projectAccessPort;

    public UpdateTransactionCategoryUseCase(
            TransactionCategoryRepository repository,
            ProjectAccessPort projectAccessPort
    ) {
        this.repository = repository;
        this.projectAccessPort = projectAccessPort;
    }

    public TransactionCategory update(
            UUID userId,
            UUID projectId,
            UUID categoryId,
            TransactionCategoryInput input
    ) {
        FieldValidator.required("name", input.name());
        FieldValidator.notNull("type", input.type());
        FieldValidator.notNull("categoryId", categoryId);
        FieldValidator.notNull("projectId", projectId);

        var hasAccess = projectAccessPort.hasAccess(userId, projectId);
        if (!hasAccess) {
            throw new EntityNotFoundException("Project with id=%s not found".formatted(projectId));
        }

        var category2 = repository.findById(categoryId).orElseThrow(() ->
                new EntityNotFoundException("Category with id=%s not found".formatted(categoryId))
        );

        // Validate name
        var category = repository.findByProjectIdAndNameAndType(projectId, input.name(), input.type());
        if (category.isPresent()) {
            throw new ResourceAlreadyExistsException("Category with name=%s already exists for this project");
        }

        category2.setType(input.type());
        category2.setName(input.name());
        return repository.save(category2);
    }
}
