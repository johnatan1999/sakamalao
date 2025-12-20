package mg.sakamalao.dashboard.core.repository;

import mg.sakamalao.dashboard.core.domain.IncomeCategoryAmount;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface IncomeDashboardPort {
    double sumByProject(UUID projectId);

    Map<YearMonth, Double> sumGroupedByMonth(UUID projectId);

    List<IncomeCategoryAmount> sumByCategory(UUID projectId);
}
