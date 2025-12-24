package mg.sakamalao.expense.core.usecase;

import mg.sakamalao.common.core.domain.entity.Expense;
import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.common.validator.FieldValidator;
import mg.sakamalao.expense.core.domain.ExpenseInput;
import mg.sakamalao.expense.core.repository.ExpenseRepository;

import java.time.LocalDate;
import java.util.UUID;

public class CreateExpenseUseCase {

    private final ExpenseRepository repository;
    private final ProjectAccessPort projectAccessPort;

    public CreateExpenseUseCase(
            ExpenseRepository repository,
            ProjectAccessPort projectAccessPort
    ) {
        this.repository = repository;
        this.projectAccessPort = projectAccessPort;
    }

    public Expense create(ExpenseInput input, UUID userId) {
        FieldValidator.notNull("input", input);
        FieldValidator.required("name", input.name());
        FieldValidator.notNull("projectId", input.projectId());
        FieldValidator.nonNegative("amount", input.amount());

        boolean hasAccess = projectAccessPort.hasAccess(userId, input.projectId());
        if (!hasAccess) {
            throw new EntityNotFoundException("Project with id=%s not found".formatted(input.projectId()));
        }
        Expense expense = Expense.builder()
                .projectId(input.projectId())
                .name(input.name())
                .description(input.description())
                .category(input.category())
                .amount(input.amount())
                .date(input.date() != null ? input.date() : LocalDate.now())
                .createdByUserId(userId)
                .createdDate(LocalDate.now())
                .build();

        return repository.save(expense);
    }
}