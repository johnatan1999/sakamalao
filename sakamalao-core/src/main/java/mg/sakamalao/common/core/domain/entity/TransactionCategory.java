package mg.sakamalao.common.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.sakamalao.common.core.domain.enums.TransactionType;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCategory {
    private UUID id;
    private UUID projectId;
    private String name;
    private TransactionType type;
    private LocalDateTime createdAt;
}
