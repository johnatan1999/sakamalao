package mg.sakamalao.expense.core.usecase;

import mg.sakamalao.common.core.domain.entity.Expense;
import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.common.validator.FieldValidator;
import mg.sakamalao.expense.core.domain.UpdateExpenseInput;
import mg.sakamalao.expense.core.repository.ExpenseRepository;

import java.time.LocalDate;
import java.util.UUID;

public class UpdateExpenseUseCase {

    private final ExpenseRepository expenseRepository;
    private final ProjectAccessPort projectAccessPort;

    public UpdateExpenseUseCase(ExpenseRepository repository, ProjectAccessPort projectAccessPort) {
        this.expenseRepository = repository;
        this.projectAccessPort = projectAccessPort;
    }

    public Expense update(UpdateExpenseInput input, UUID userId) {

        FieldValidator.notNull("expenseId", input.expenseId());

        Expense expense = expenseRepository.findById(input.expenseId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Expense not found")
                );

        boolean hasAccess = projectAccessPort.hasAccess(userId, expense.getProjectId());
        if (!hasAccess) {
            throw new EntityNotFoundException("Expense not found");
        }

        Expense updated = new Expense(
                input.expenseId(),
                input.name(),
                input.description(),
                input.category(),
                input.amount(),
                input.date(),
                expense.getCreatedDate(), // unchanged
                LocalDate.now(), // updated date
                expense.getProjectId(),
                expense.getCreatedByUserId(),
                userId
        );

        return expenseRepository.save(updated);
    }
}