package mg.sakamalao.transaction.core.usecase.expense;

import mg.sakamalao.common.core.domain.entity.Expense;
import mg.sakamalao.common.core.usecase.ProjectAccessCheckerUseCase;
import mg.sakamalao.common.validator.FieldValidator;
import mg.sakamalao.transaction.core.repository.expense.ExpenseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FindExpensesUseCase {
    private final ExpenseRepository repository;
    private final ProjectAccessCheckerUseCase projectAccess;

    public FindExpensesUseCase(ExpenseRepository repository, ProjectAccessCheckerUseCase projectAccessPort) {
        this.repository = repository;
        this.projectAccess = projectAccessPort;
    }

    public List<Expense> findByProject(UUID projectId, UUID userId) {
        FieldValidator.notNull("userId", userId);
        FieldValidator.notNull("projectId", projectId);
        projectAccess.checkAccess(userId, projectId);
        return repository.findByProjectId(projectId);
    }

    public Optional<Expense> findById(
            UUID userId,
            UUID projectId,
            UUID expenseId
    ) {
        FieldValidator.notNull("userId", userId);
        FieldValidator.notNull("projectId", projectId);
        FieldValidator.notNull("incomeId", expenseId);
        projectAccess.checkAccess(userId, projectId);
        return repository.findById(expenseId);
    }
}
