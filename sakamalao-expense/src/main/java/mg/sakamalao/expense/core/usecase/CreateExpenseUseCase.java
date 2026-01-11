package mg.sakamalao.expense.core.usecase;

import mg.sakamalao.common.core.domain.entity.Expense;
import mg.sakamalao.common.core.domain.entity.TransactionCategory;
import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.core.port.CategoryAccessPort;
import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.common.validator.FieldValidator;
import mg.sakamalao.expense.core.domain.ExpenseInput;
import mg.sakamalao.expense.core.repository.ExpenseRepository;

import java.time.LocalDate;
import java.util.UUID;

public class CreateExpenseUseCase {

    private final ExpenseRepository repository;
    private final ProjectAccessPort projectAccessPort;
    private final CategoryAccessPort categoryAccessPort;

    public CreateExpenseUseCase(
            ExpenseRepository repository,
            ProjectAccessPort projectAccessPort,
            CategoryAccessPort categoryAccess
    ) {
        this.repository = repository;
        this.projectAccessPort = projectAccessPort;
        this.categoryAccessPort = categoryAccess;
    }

    public Expense create(ExpenseInput input, UUID userId) {
        FieldValidator.notNull("input", input);
        FieldValidator.required("name", input.name());
        FieldValidator.notNull("projectId", input.projectId());
        FieldValidator.notNull("categoryId", input.categoryId());
        FieldValidator.nonNegative("amount", input.amount());

        boolean hasAccess = projectAccessPort.hasAccess(userId, input.projectId());
        if (!hasAccess) {
            throw new EntityNotFoundException("Project with id=%s not found".formatted(input.projectId()));
        }

        var categoryExists = categoryAccessPort.exists(input.categoryId(), input.projectId());
        if (!categoryExists) {
            throw new EntityNotFoundException("Category with id=%s not found".formatted(input.categoryId()));
        }
        Expense expense = Expense.builder()
                .projectId(input.projectId())
                .name(input.name())
                .description(input.description())
                .category(
                        TransactionCategory.builder()
                                .id(input.categoryId())
                                .build()
                )
                .amount(input.amount())
                .date(input.date() != null ? input.date() : LocalDate.now())
                .createdByUserId(userId)
                .createdDate(LocalDate.now())
                .build();

        return repository.save(expense);
    }
}