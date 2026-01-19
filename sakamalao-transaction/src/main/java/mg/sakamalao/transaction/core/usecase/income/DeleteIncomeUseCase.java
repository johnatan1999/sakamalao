package mg.sakamalao.transaction.core.usecase.income;

import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.transaction.core.repository.income.IncomeRepository;

import java.util.UUID;

public class DeleteIncomeUseCase {
    private final IncomeRepository repository;
    private final ProjectAccessPort projectAccess;

    public DeleteIncomeUseCase(IncomeRepository repository, ProjectAccessPort projectAccess) {
        this.repository = repository;
        this.projectAccess = projectAccess;
    }

    public void delete(UUID incomeId, UUID currentUserId) {
        var income = repository.findById(incomeId).orElseThrow(() -> new EntityNotFoundException(
                "Income with id=%s not found".formatted(incomeId)
        ));

        var userHasAccess = projectAccess.hasAccess(currentUserId, income.getProjectId());
        if (!userHasAccess) {
            throw new EntityNotFoundException(
                    "Income with id=%s not found".formatted(incomeId)
            );
        }
        repository.deleteById(incomeId);
    }
}
