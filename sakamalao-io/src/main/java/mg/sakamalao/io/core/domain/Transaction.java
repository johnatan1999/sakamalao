package mg.sakamalao.io.core.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Transaction {
    private String name;
    private String description;
    private String category;
    private double amount;
    private LocalDate date;
    private TransactionType type;
}
