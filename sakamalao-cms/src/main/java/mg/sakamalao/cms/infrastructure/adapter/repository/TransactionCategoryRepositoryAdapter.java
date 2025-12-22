package mg.sakamalao.cms.infrastructure.adapter.repository;

import mg.sakamalao.cms.core.domain.TransactionCategory;
import mg.sakamalao.cms.core.repository.TransactionCategoryRepository;
import mg.sakamalao.common.core.domain.enums.ExpenseCategory;
import mg.sakamalao.common.core.domain.enums.IncomeCategory;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TransactionCategoryRepositoryAdapter implements TransactionCategoryRepository {

    @Override
    public List<TransactionCategory> getTransactionCategories(UUID projectId) {
        var incomes = IncomeCategory.values();
        var expenses = ExpenseCategory.values();
        var result = new HashSet<TransactionCategory>();
        result.addAll(Arrays.stream(incomes).map(ic -> new TransactionCategory(ic.name().toLowerCase(), ic.name())).toList());
        result.addAll(Arrays.stream(expenses).map(ic -> new TransactionCategory(ic.name().toLowerCase(), ic.name())).toList());
        return result.stream().sorted(Comparator.comparing(TransactionCategory::id)).toList();
    }
}
