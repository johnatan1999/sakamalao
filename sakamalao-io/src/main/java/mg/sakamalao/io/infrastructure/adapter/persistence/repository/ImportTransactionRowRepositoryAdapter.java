package mg.sakamalao.io.infrastructure.adapter.persistence.repository;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.io.core.domain.ImportStatus;
import mg.sakamalao.io.core.domain.ImportTransactionRow;
import mg.sakamalao.io.core.domain.input.ImportTransactionRowInput;
import mg.sakamalao.io.core.repository.ImportTransactionRowRepository;
import mg.sakamalao.io.infrastructure.adapter.persistence.jpa.ImportTransactionRowJpaRepository;
import mg.sakamalao.io.infrastructure.adapter.persistence.mapper.ImportTransactionRowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ImportTransactionRowRepositoryAdapter implements ImportTransactionRowRepository {

    private final ImportTransactionRowJpaRepository repository;

    @Override
    public void insert(List<ImportTransactionRowInput> transactions) {
        var rows = transactions.stream().map(ImportTransactionRowMapper::mapToDbEntity).toList();
        repository.saveAll(rows);
    }

    @Override
    public void updateStatus(UUID id, ImportStatus importStatus) {
        var entity = repository.findById(id).orElse(null);
        if (entity == null) {
            throw new EntityNotFoundException("Transaction with id=%s not found".formatted(id.toString()));
        }
        entity.setStatus(importStatus);
        repository.save(entity);
    }

    @Override
    public List<ImportTransactionRow> findBySession(UUID sessionId) {
        return repository.findAllBySessionId(sessionId)
                .stream()
                .map(ImportTransactionRowMapper::mapToDomain)
                .toList();
    }
}
