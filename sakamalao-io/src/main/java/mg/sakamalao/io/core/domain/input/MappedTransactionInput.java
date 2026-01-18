package mg.sakamalao.io.core.domain.input;

import lombok.Data;
import mg.sakamalao.common.core.domain.enums.TransactionType;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class MappedTransactionInput {
    private UUID id;
    private String name;
    private String description;
    private String categoryId;
    private String category;
    private double amount;
    private LocalDateTime date;
    private TransactionType type;
}
