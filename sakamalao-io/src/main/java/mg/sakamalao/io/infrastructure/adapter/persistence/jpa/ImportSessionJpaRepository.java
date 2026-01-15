package mg.sakamalao.io.infrastructure.adapter.persistence.jpa;

import mg.sakamalao.io.core.domain.ImportStatus;
import mg.sakamalao.io.infrastructure.adapter.persistence.entity.ImportSessionDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ImportSessionJpaRepository extends JpaRepository<ImportSessionDbEntity, UUID> {
    List<ImportSessionDbEntity> findAllByStatus(ImportStatus status);
}
