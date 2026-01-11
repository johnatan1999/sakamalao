package mg.sakamalao.expense.core.usecase;

import mg.sakamalao.common.core.domain.entity.Expense;
import mg.sakamalao.common.core.domain.entity.TransactionCategory;
import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.core.port.CategoryAccessPort;
import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.common.validator.FieldValidator;
import mg.sakamalao.expense.core.domain.UpdateExpenseInput;
import mg.sakamalao.expense.core.repository.ExpenseRepository;

import java.time.LocalDate;
import java.util.UUID;

public class UpdateExpenseUseCase {

    private final ExpenseRepository expenseRepository;
    private final ProjectAccessPort projectAccessPort;
    private final CategoryAccessPort categoryAccess;

    public UpdateExpenseUseCase(
            ExpenseRepository expenseRepository,
            ProjectAccessPort projectAccessPort,
            CategoryAccessPort categoryAccess
    ) {
        this.expenseRepository = expenseRepository;
        this.projectAccessPort = projectAccessPort;
        this.categoryAccess = categoryAccess;
    }

    public Expense update(UpdateExpenseInput input, UUID userId) {
        FieldValidator.notNull("input", input);
        FieldValidator.notNull("expenseId", input.expenseId());
        FieldValidator.required("name", input.name());
        FieldValidator.notNull("projectId", input.projectId());
        FieldValidator.nonNegative("amount", input.amount());

        Expense expense = expenseRepository.findById(input.expenseId())
                .orElseThrow(() ->
                     new EntityNotFoundException("Expense with id=%s not found".formatted(input.expenseId()))
                );

        boolean hasAccess = projectAccessPort.hasAccess(userId, expense.getProjectId());
        if (!hasAccess) {
            throw new EntityNotFoundException("Expense with id=%s not found".formatted(input.expenseId()));
        }

        var categoryExists = categoryAccess.exists(input.categoryId(), input.projectId());
        if (!categoryExists) {
            throw new EntityNotFoundException("Category with id=%s not found".formatted(input.categoryId()));
        }

        Expense updated = Expense.builder()
                .id(input.expenseId())
                .projectId(expense.getProjectId())
                .name(input.name())
                .description(input.description())
                .category(
                        TransactionCategory.builder()
                                .id(input.categoryId())
                                .build()
                )
                .amount(input.amount())
                .date(input.date())
                .createdByUserId(expense.getCreatedByUserId())
                .createdDate(expense.getCreatedDate())
                .updatedDate(LocalDate.now())
                .updatedByUserId(userId)
                .build();

        return expenseRepository.save(updated);
    }
}