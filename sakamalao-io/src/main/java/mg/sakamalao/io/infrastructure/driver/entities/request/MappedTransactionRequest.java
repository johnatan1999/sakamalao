package mg.sakamalao.io.infrastructure.driver.entities.request;

import lombok.Data;
import mg.sakamalao.common.core.domain.enums.TransactionType;
import mg.sakamalao.io.core.domain.input.MappedTransactionInput;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class MappedTransactionRequest {
    private UUID id;
    private String name;
    private String description;
    private String categoryId;
    private String category;
    private double amount;
    private LocalDateTime date;
    private TransactionType type;

    public MappedTransactionInput mapToInput() {
        var input = new MappedTransactionInput();
        input.setName(name);
        input.setId(id);
        input.setDescription(description);
        input.setCategoryId(categoryId);
        input.setCategory(category);
        input.setAmount(amount);
        input.setDate(date);
        input.setType(type);
        return input;
    }
}
