package mg.sakamalao.cms.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.sakamalao.common.core.domain.enums.TransactionType;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity()
@Table(
        name = "transaction_category",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"project_id", "name"})
        }
)
public class TransactionCategoryDbEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(length = 100)
    private String name;

    @Column(name = "project_id", nullable = false)
    private UUID projectId;

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
