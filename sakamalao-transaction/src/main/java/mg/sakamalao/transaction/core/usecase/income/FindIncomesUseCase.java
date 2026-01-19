package mg.sakamalao.transaction.core.usecase.income;

import mg.sakamalao.common.core.domain.entity.Income;
import mg.sakamalao.common.core.usecase.ProjectAccessCheckerUseCase;
import mg.sakamalao.common.validator.FieldValidator;
import mg.sakamalao.transaction.core.repository.income.IncomeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FindIncomesUseCase {
    private final IncomeRepository repository;
    private final ProjectAccessCheckerUseCase projectAccess;

    public FindIncomesUseCase(IncomeRepository repository, ProjectAccessCheckerUseCase projectAccess) {
        this.repository = repository;
        this.projectAccess = projectAccess;
    }

    public List<Income> findByProject(UUID projectId, UUID userId) {
        projectAccess.checkAccess(userId, projectId);
        return repository.findByProjectId(projectId);
    }

    public Optional<Income> findById(
            UUID userId,
            UUID projectId,
            UUID incomeId
    ) {
        FieldValidator.notNull("userId", userId);
        FieldValidator.notNull("projectId", projectId);
        FieldValidator.notNull("incomeId", incomeId);
        projectAccess.checkAccess(userId, projectId);
        return repository.findById(incomeId);
    }
}
