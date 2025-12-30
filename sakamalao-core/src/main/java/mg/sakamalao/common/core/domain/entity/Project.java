package mg.sakamalao.common.core.domain.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Project extends BaseEntity {
    private String name;
    private String description;
    private String currency;
    private UUID owerId;
    private double budget;
    private LocalDate startDate;
    private LocalDate endDate;

    public Project(
            UUID id,
            String name,
            String description,
            String currency,
            UUID owerId,
            double budget,
            LocalDate startDate,
            LocalDate endDate
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.owerId = owerId;
        this.budget = budget;
        this.startDate = startDate;
        this.endDate = endDate;
        this.currency = currency;
    }
}
