package mg.sakamalao.io.infrastructure.adapter.persistence.mapper;

import mg.sakamalao.io.core.domain.ImportStatus;
import mg.sakamalao.io.core.domain.ImportTransactionRow;
import mg.sakamalao.io.core.domain.input.ImportTransactionRowInput;
import mg.sakamalao.io.infrastructure.adapter.persistence.entity.ImportSessionDbEntity;
import mg.sakamalao.io.infrastructure.adapter.persistence.entity.ImportTransactionRowDbEntity;

public final class ImportTransactionRowMapper {

    private ImportTransactionRowMapper() {}

    public static ImportTransactionRowDbEntity mapToDbEntity(ImportTransactionRowInput input) {
        var e = new ImportTransactionRowDbEntity();
        e.setRawAmount(input.amount());
        e.setType(input.type());
        e.setRawCategory(input.category());
        e.setRawName(input.name());
        e.setRawDate(input.date());
        e.setStatus(ImportStatus.PENDING);
        var session = new ImportSessionDbEntity();
        session.setId(input.sessionId());
        e.setSession(session);
        return e;
    }

    public static ImportTransactionRow mapToDomain(ImportTransactionRowDbEntity e) {
        return ImportTransactionRow.builder()
                .amount(e.getRawAmount())
                .category(e.getRawCategory())
                .id(e.getId())
                .sessionId(e.getSession().getId())
                .name(e.getRawName())
                .type(e.getType())
                .date(e.getRawDate())
                .build();
    }
}
