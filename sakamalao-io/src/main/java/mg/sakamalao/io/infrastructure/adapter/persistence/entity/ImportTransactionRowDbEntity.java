package mg.sakamalao.io.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.sakamalao.io.core.domain.ImportStatus;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "import_transaction_row")
@NoArgsConstructor
@AllArgsConstructor
public class ImportTransactionRowDbEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @ManyToOne
    private ImportSessionDbEntity session;

    @Column
    private String rawName;

    @Column
    private String rawCategory;

    @Column
    private double rawAmount;

    @Column
    private LocalDateTime rawDate;

    @Column
    private UUID mappedCategoryId;   // null tant que pas validé

    @Column
    private String type; // EXPENSE / INCOME

    @Column
    @Enumerated(EnumType.STRING)
    private ImportStatus status;
}
