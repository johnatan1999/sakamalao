package mg.sakamalao.io.core.usecase;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.core.domain.exception.EntityNotFoundException;
import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.common.validator.FieldValidator;
import mg.sakamalao.io.core.domain.ImportSession;
import mg.sakamalao.io.core.domain.input.ImportSessionInput;
import mg.sakamalao.io.core.domain.input.ImportTransactionRowInput;
import mg.sakamalao.io.core.repository.ImportSessionRepository;
import mg.sakamalao.io.core.repository.ImportTransactionRowRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CreateImportSessionUseCase {
    private final ProjectAccessPort projectAccess;
    private final ImportSessionRepository importSessionRepository;
    private final ImportTransactionRowRepository importTransactionRowRepository;

    public ImportSession createSession(UUID projectId, UUID userId) {
        FieldValidator.notNull("projectId", projectId);
        FieldValidator.notNull("userId", userId);

        var hasAccess = projectAccess.hasAccess(userId, projectId);
        if (!hasAccess) {
            throw new EntityNotFoundException("Project with id=%s not found".formatted(projectId));
        }
        return importSessionRepository.create(new ImportSessionInput(
                userId,
                projectId,
                LocalDateTime.now()
        ));
    }

    public List<ImportTransactionRowInput> createRowsWithSession(List<ImportTransactionRowInput> rowsData, UUID sessionId) {
        return rowsData.stream()
                .map(d -> ImportTransactionRowInput.builder()
                        .sessionId(sessionId)
                        .name(d.name())
                        .category(d.category())
                        .amount(d.amount())
                        .type(d.type())
                        .build())
                .collect(Collectors.toList());
    }

}
