package mg.sakamalao.io.infrastructure.adapter.persistence.repository;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.io.core.domain.ImportSession;
import mg.sakamalao.io.core.domain.ImportStatus;
import mg.sakamalao.io.core.domain.input.ImportSessionInput;
import mg.sakamalao.io.core.repository.ImportSessionRepository;
import mg.sakamalao.io.infrastructure.adapter.persistence.jpa.ImportSessionJpaRepository;
import mg.sakamalao.io.infrastructure.adapter.persistence.mapper.ImportSessionMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ImportSessionRepositoryAdapter implements ImportSessionRepository {

    private final ImportSessionJpaRepository repository;

    @Override
    public ImportSession create(ImportSessionInput input) {
        var entity = ImportSessionMapper.mapToDbEntity(input);
        var newEntity = repository.save(entity);
        return ImportSessionMapper.mapToDomain(newEntity);
    }

    @Override
    public List<ImportSession> findPendingImportsByProjectId(UUID projectId) {
        return repository.findAllByStatusAndProjectId(ImportStatus.PENDING, projectId)
                .stream()
                .map(ImportSessionMapper::mapToDomain)
                .toList();
    }

    @Override
    public void update(ImportSession importSession) {
        var entity = ImportSessionMapper.mapToDbEntity(importSession);
        repository.save(entity);
    }
}
