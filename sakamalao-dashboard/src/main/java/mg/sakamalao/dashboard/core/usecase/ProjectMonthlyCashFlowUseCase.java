package mg.sakamalao.dashboard.core.usecase;

import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.dashboard.core.domain.MonthlyCashFlow;
import mg.sakamalao.dashboard.core.repository.ExpenseDashboardPort;
import mg.sakamalao.dashboard.core.repository.IncomeDashboardPort;

import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

public class ProjectMonthlyCashFlowUseCase {
    private final IncomeDashboardPort incomeRepository;
    private final ExpenseDashboardPort expenseRepository;
    private final ProjectAccessPort projectAccess;

    public ProjectMonthlyCashFlowUseCase(
            IncomeDashboardPort incomeRepository,
            ExpenseDashboardPort expenseRepository,
            ProjectAccessPort projectAccess
    ) {
        this.incomeRepository = incomeRepository;
        this.expenseRepository = expenseRepository;
        this.projectAccess = projectAccess;
    }

    public List<MonthlyCashFlow> execute(UUID projectId, UUID userId) {

        var hasAccess = projectAccess.hasAccess(userId, projectId);
        if (!hasAccess) {
            throw new EntityNotFoundException("Project with id=%s not found".formatted(projectId));
        }

        Map<YearMonth, Double> incomes =
                incomeRepository.sumGroupedByMonth(projectId);

        Map<YearMonth, Double> expenses =
                expenseRepository.sumGroupedByMonth(projectId);

        return Stream.concat(incomes.keySet().stream(), expenses.keySet().stream())
                .distinct()
                .map(month -> new MonthlyCashFlow(
                        month,
                        incomes.getOrDefault(month, 0.0),
                        expenses.getOrDefault(month, 0.0)
                ))
                .sorted(Comparator.comparing(MonthlyCashFlow::month))
                .toList();
    }
}
