package mg.sakamalao.transaction.core.usecase.income;

import mg.sakamalao.common.core.domain.entity.Income;
import mg.sakamalao.common.core.domain.entity.TransactionCategory;
import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.core.port.CategoryAccessPort;
import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.common.validator.FieldValidator;
import mg.sakamalao.transaction.core.domain.income.UpdateIncomeInput;
import mg.sakamalao.transaction.core.repository.income.IncomeRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public class UpdateIncomeUseCase {
    private final IncomeRepository repository;
    private final ProjectAccessPort projectAccess;
    private final CategoryAccessPort categoryAccess;

    public UpdateIncomeUseCase(
            IncomeRepository repository,
            ProjectAccessPort projectAccess,
            CategoryAccessPort categoryAccess
    ) {
        this.repository = repository;
        this.projectAccess = projectAccess;
        this.categoryAccess = categoryAccess;
    }

    public Income update(UpdateIncomeInput input, UUID userId) {
        FieldValidator.notNull("input", input);
        FieldValidator.notNull("incomeId", input.incomeId());
        FieldValidator.required("name", input.name());
        FieldValidator.notNull("projectId", input.projectId());
        FieldValidator.nonNegative("amount", input.amount());

        Income income = repository.findById(input.incomeId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Income with id=%s not found".formatted(input.incomeId()))
                );

        boolean hasAccess = projectAccess.hasAccess(userId, income.getProjectId());
        if (!hasAccess) {
            throw new EntityNotFoundException("Income with id=%s not found".formatted(input.incomeId()));
        }

        var categoryExists = categoryAccess.exists(input.categoryId(), input.projectId());
        if (!categoryExists) {
            throw new EntityNotFoundException("Category with id=%s not found".formatted(input.categoryId()));
        }

        Income updated = new Income();
        updated.setId(input.incomeId());
        updated.setProjectId(input.projectId());
        updated.setName(input.name());
        updated.setDescription(input.description());
        updated.setCategory(
                TransactionCategory.builder()
                        .id(input.categoryId())
                        .build()
        );
        updated.setAmount(input.amount());
        updated.setDate(input.date());

        updated.setCreatedDate(income.getCreatedDate()); // unchanged
        updated.setCreatedByUserId(income.getCreatedByUserId()); // unchanged

        updated.setUpdatedDate(LocalDateTime.now());
        updated.setUpdatedByUserId(userId);

        return repository.save(updated);
    }
}
