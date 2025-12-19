package mg.sakamalao.income.core.usecase;

import mg.sakamalao.common.core.domain.entity.Income;
import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.common.validator.FieldValidator;
import mg.sakamalao.income.core.domain.IncomeInput;
import mg.sakamalao.income.core.repository.IncomeRepository;

import java.time.LocalDate;
import java.util.UUID;

public class CreateIncomeUseCase {
    private final IncomeRepository repository;
    private final ProjectAccessPort projectAccess;

    public CreateIncomeUseCase(IncomeRepository repository, ProjectAccessPort projectAccess) {
        this.repository = repository;
        this.projectAccess = projectAccess;
    }

    public Income create(IncomeInput input, UUID userId) {
        FieldValidator.notNull("input", input);
        FieldValidator.required("name", input.name());
        FieldValidator.notNull("userId", userId);
        FieldValidator.notNull("projectId", input.projectId());
        FieldValidator.nonNegative("amount", input.amount());

        boolean hasAccess = projectAccess.hasAccess(userId, input.projectId());
        if (!hasAccess) {
            throw new EntityNotFoundException("Project with id=%s not found".formatted(input.projectId()));
        }

        Income income = new Income();
        income.setProjectId(input.projectId());
        income.setName(input.name());
        income.setDescription(input.description());
        income.setCategory(input.category());
        income.setAmount(input.amount());
        income.setDate(input.date() != null ? input.date() : LocalDate.now());
        income.setCreatedDate(LocalDate.now());
        income.setCreatedByUserId(userId);

        return repository.save(income);
    }
}
