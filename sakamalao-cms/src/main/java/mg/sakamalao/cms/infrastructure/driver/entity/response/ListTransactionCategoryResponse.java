package mg.sakamalao.cms.infrastructure.driver.entity.response;

import mg.sakamalao.common.core.domain.entity.TransactionCategory;

import java.util.List;

public record ListTransactionCategoryResponse(
        List<TransactionCategory> incomes,
        List<TransactionCategory> expenses
) {
}
