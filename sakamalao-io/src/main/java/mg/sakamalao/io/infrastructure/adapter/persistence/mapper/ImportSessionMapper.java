package mg.sakamalao.io.infrastructure.adapter.persistence.mapper;

import mg.sakamalao.io.core.domain.ImportSession;
import mg.sakamalao.io.core.domain.ImportStatus;
import mg.sakamalao.io.core.domain.input.ImportSessionInput;
import mg.sakamalao.io.infrastructure.adapter.persistence.entity.ImportSessionDbEntity;

public final class ImportSessionMapper {
    private  ImportSessionMapper() {
    }

    public static ImportSessionDbEntity mapToDbEntity(ImportSessionInput input) {
        var e = new ImportSessionDbEntity();
        e.setStatus(ImportStatus.PENDING);
        e.setCreatedAt(input.createdDate());
        e.setProjectId(input.projectId());
        e.setCreatedBy(input.userId());
        return e;
    }

    public static ImportSessionDbEntity mapToDbEntity(ImportSession input) {
        var e = new ImportSessionDbEntity();
        e.setStatus(ImportStatus.PENDING);
        e.setCreatedAt(input.createdAt());
        e.setId(input.id());
        e.setProjectId(input.projectId());
        e.setCreatedBy(input.userId());
        return e;
    }

    public static ImportSession mapToDomain(ImportSessionDbEntity e) {
        return ImportSession.builder()
                .id(e.getId())
                .createdAt(e.getCreatedAt())
                .projectId(e.getProjectId())
                .userId(e.getCreatedBy())
                .build();
    }
}
