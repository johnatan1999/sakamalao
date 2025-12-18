package mg.sakamalao.expense.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.sakamalao.common.core.domain.enums.ExpenseCategory;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "expenses")
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDbEntity {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalDate createdDate;

    @Column(nullable = true)
    private LocalDate updatedDate;

    @Column(name = "project_id", nullable = false)
    private UUID projectId;

    @Column(name = "created_by_user_id", nullable = false)
    private UUID createdByUserId;

    @Column(name = "updated_by_user_id", nullable = true)
    private UUID updatedByUserId;

    @Column(length = 100)
    private ExpenseCategory category;

}
