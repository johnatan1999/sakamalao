package mg.sakamalao.cms.infrastructure.driver.entity.response;

import mg.sakamalao.cms.core.domain.TransactionCategory;

import java.util.List;

public record ListTransactionCategoryResponse(
        List<TransactionCategory> incomes,
        List<TransactionCategory> expenses
) {
}
