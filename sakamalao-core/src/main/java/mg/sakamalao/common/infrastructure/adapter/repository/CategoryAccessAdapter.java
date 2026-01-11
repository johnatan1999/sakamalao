package mg.sakamalao.common.infrastructure.adapter.repository;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.core.port.CategoryAccessPort;
import mg.sakamalao.common.infrastructure.adapter.jpa.TransactionCategoryJpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CategoryAccessAdapter implements CategoryAccessPort {

    private final TransactionCategoryJpaRepository repository;

    @Override
    public boolean exists(UUID categoryId, UUID projectId) {
        var category = repository.findById(categoryId);
        return category.map(transactionCategoryDbEntity ->
                transactionCategoryDbEntity.getProjectId().equals(projectId)
        ).orElse(false);
    }
}
