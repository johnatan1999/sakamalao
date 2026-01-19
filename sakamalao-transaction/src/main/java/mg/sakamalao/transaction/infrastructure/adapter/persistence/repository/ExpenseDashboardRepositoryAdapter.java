package mg.sakamalao.transaction.infrastructure.adapter.persistence.repository;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.core.domain.enums.ExpenseCategory;
import mg.sakamalao.dashboard.core.domain.ExpenseCategoryAmount;
import mg.sakamalao.dashboard.core.repository.ExpenseDashboardPort;
import mg.sakamalao.transaction.infrastructure.adapter.persistence.jpa.ExpenseStatJpaRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ExpenseDashboardRepositoryAdapter implements ExpenseDashboardPort {
    private final ExpenseStatJpaRepository repository;

    @Override
    public double sumByProject(UUID projectId) {
        return repository.sumByProject(projectId);
    }

    @Override
    public Map<YearMonth, Double> sumGroupedByMonth(UUID projectId) {
        return repository.sumGroupedByMonth(projectId)
                .stream()
                .collect(Collectors.toMap(
                        r -> YearMonth.of((int) r[0], (int) r[1]),
                        r -> (double) r[2]
                ));
    }

    @Override
    public List<ExpenseCategoryAmount> sumByCategory(UUID projectId) {
        return repository.sumByCategory(projectId)
                .stream()
                .collect(Collectors.toMap(
                        r -> (ExpenseCategory) r[0],
                        r -> (double) r[1]
                ))
                .entrySet().stream()
                .map(i -> new ExpenseCategoryAmount(i.getKey(), i.getValue()))
                .collect(Collectors.toList());
    }
}
