package mg.sakamalao.transaction.core.usecase.expense;

import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.transaction.core.repository.expense.ExpenseRepository;

import java.util.UUID;

public class DeleteExpenseUseCase {

    private final mg.sakamalao.transaction.core.repository.expense.ExpenseRepository repository;
    private final ProjectAccessPort projectAccess;

    public DeleteExpenseUseCase(ExpenseRepository repository, ProjectAccessPort projectAccess) {
        this.repository = repository;
        this.projectAccess = projectAccess;
    }

    public void deleteExpense(UUID expenseId, UUID currentUserId) {
        var expense = repository.findById(expenseId).orElseThrow(() -> new EntityNotFoundException(
                "Expense with id=%s not found".formatted(expenseId)
        ));

        var userHasAccess = projectAccess.hasAccess(currentUserId, expense.getProjectId());
        if (!userHasAccess) {
            throw new EntityNotFoundException(
                    "Expense with id=%s not found".formatted(expenseId)
            );
        }
        repository.deleteById(expenseId);
    }
}
