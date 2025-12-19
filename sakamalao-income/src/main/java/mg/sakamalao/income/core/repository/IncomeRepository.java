package mg.sakamalao.income.core.repository;

import mg.sakamalao.common.core.domain.entity.Income;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IncomeRepository {
    Income save(Income income);
    Optional<Income> findById(UUID id);
    List<Income> findByProjectId(UUID projectId);
    void deleteById(UUID id);
}
