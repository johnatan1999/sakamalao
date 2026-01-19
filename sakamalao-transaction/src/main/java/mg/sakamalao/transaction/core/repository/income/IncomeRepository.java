package mg.sakamalao.transaction.core.repository.income;

import mg.sakamalao.common.core.domain.entity.Income;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IncomeRepository {
    Income save(Income income);
    void saveAll(List<Income> incomes);
    Optional<Income> findById(UUID id);
    List<Income> findByProjectId(UUID projectId);
    void deleteById(UUID id);
}
