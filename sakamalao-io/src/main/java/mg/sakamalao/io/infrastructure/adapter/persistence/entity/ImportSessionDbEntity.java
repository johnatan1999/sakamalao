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
@Table(name = "import_session")
@NoArgsConstructor
@AllArgsConstructor
public class ImportSessionDbEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column
    private UUID projectId;

    @Column
    private UUID createdBy;

    @Column
    @Enumerated(EnumType.STRING)
    private ImportStatus status; // PENDING, MAPPING, IMPORTED, FAILED

    @Column
    private LocalDateTime createdAt;
}
