package mg.sakamalao.expense.core.repository;

import mg.sakamalao.common.core.domain.entity.Expense;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExpenseRepository {
    Expense save(Expense expense);
    Optional<Expense> findById(UUID id);
    List<Expense> findByProjectId(UUID projectId);
    void deleteById(UUID id);
    void saveAll(List<Expense> expenses);
}
