package mg.sakamalao.common.core.domain.enums;

import lombok.Getter;

@Getter
public enum TransactionType {
    INCOME("income"),
    EXPENSE("expense");

    private final String value;

    TransactionType(String value) {
        this.value = value;
    }

}
