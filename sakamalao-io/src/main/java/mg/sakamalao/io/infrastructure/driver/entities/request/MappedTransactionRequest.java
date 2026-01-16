package mg.sakamalao.io.infrastructure.driver.entities.request;

import lombok.Data;
import mg.sakamalao.common.core.domain.enums.TransactionType;
import mg.sakamalao.io.core.domain.input.MappedTransactionInput;

import java.time.LocalDateTime;

@Data
public class MappedTransactionRequest {
    private String name;
    private String description;
    private String categoryId;
    private double amount;
    private LocalDateTime date;
    private TransactionType type;

    public MappedTransactionInput mapToInput() {
        var input = new MappedTransactionInput();
        input.setName(name);
        input.setDescription(description);
        input.setCategoryId(categoryId);
        input.setAmount(amount);
        input.setDate(date);
        input.setType(type);
        return input;
    }
}
