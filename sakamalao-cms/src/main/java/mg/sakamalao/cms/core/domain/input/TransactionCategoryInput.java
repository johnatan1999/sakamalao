package mg.sakamalao.cms.core.domain.input;

import mg.sakamalao.common.core.domain.enums.TransactionType;

public record TransactionCategoryInput(
        String name,
        TransactionType type
) {
}
