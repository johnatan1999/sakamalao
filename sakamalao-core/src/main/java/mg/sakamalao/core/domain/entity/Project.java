package mg.sakamalao.core.domain.entity;

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
    private UUID owerId;
    private double budget;
    private LocalDate startDate;
    private LocalDate endDate;
}
