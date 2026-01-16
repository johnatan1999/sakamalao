package mg.sakamalao.io.infrastructure.driver.entities.request;

import lombok.Data;
import mg.sakamalao.io.core.domain.input.ImportTransactionRowInput;

import java.time.LocalDateTime;

@Data
public class ImportTransactionRequest {
    private String name;
    private String description;
    private String category;
    private double amount;
    private LocalDateTime date;
    private String type;

    public ImportTransactionRowInput mapToInput() {
        return ImportTransactionRowInput.builder()
                .date(date)
                .description(description)
                .amount(amount)
                .type(type)
                .name(name)
                .category(category)
                .build();
    }
}
