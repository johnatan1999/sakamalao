package mg.sakamalao.transaction.infrastructure.adapter.persistence.repository;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.core.domain.enums.IncomeCategory;
import mg.sakamalao.dashboard.core.domain.IncomeCategoryAmount;
import mg.sakamalao.dashboard.core.repository.IncomeDashboardPort;
import mg.sakamalao.transaction.infrastructure.adapter.persistence.jpa.IncomeStatJpaRepository;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class IncomeDashboardRepositoryAdapter implements IncomeDashboardPort {
    private final IncomeStatJpaRepository repository;

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
    public List<IncomeCategoryAmount> sumByCategory(UUID projectId) {
        return repository.sumByCategory(projectId)
                .stream()
                .collect(Collectors.toMap(
                        r -> (IncomeCategory) r[0],
                        r -> (double) r[1]
                ))
                .entrySet().stream()
                .map(i -> new IncomeCategoryAmount(i.getKey(), i.getValue()))
                .collect(Collectors.toList());
    }
}
