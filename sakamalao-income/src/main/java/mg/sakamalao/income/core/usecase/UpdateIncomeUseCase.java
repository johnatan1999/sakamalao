package mg.sakamalao.income.core.usecase;

import mg.sakamalao.common.core.domain.entity.Income;
import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.common.validator.FieldValidator;
import mg.sakamalao.income.core.domain.UpdateIncomeInput;
import mg.sakamalao.income.core.repository.IncomeRepository;

import java.time.LocalDate;
import java.util.UUID;

public class UpdateIncomeUseCase {
    private final IncomeRepository repository;
    private final ProjectAccessPort projectAccess;

    public UpdateIncomeUseCase(IncomeRepository repository, ProjectAccessPort projectAccess) {
        this.repository = repository;
        this.projectAccess = projectAccess;
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

        Income updated = new Income();
        updated.setId(input.incomeId());
        updated.setProjectId(input.projectId());
        updated.setName(input.name());
        updated.setDescription(input.description());
        updated.setCategory(input.category());
        updated.setAmount(input.amount());
        updated.setDate(input.date());

        updated.setCreatedDate(income.getCreatedDate()); // unchanged
        updated.setCreatedByUserId(income.getCreatedByUserId()); // unchanged

        updated.setUpdatedDate(LocalDate.now());
        updated.setUpdatedByUserId(userId);

        return repository.save(updated);
    }
}
