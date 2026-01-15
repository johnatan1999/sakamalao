package mg.sakamalao.io.infrastructure.adapter;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mg.sakamalao.io.core.domain.input.ImportTransactionRowInput;
import mg.sakamalao.io.core.repository.ImportTransactionRowRepository;
import mg.sakamalao.io.core.usecase.CreateImportSessionUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateImportSessionService {
    private final ImportTransactionRowRepository repository;
    private final CreateImportSessionUseCase useCase;

    @Transactional
    public void importData(UUID projectId, UUID userId, List<ImportTransactionRowInput> rows) {
        var session = useCase.createSession(projectId, userId);
        var data = useCase.createRowsWithSession(rows, session.id());
        repository.insert(data);
    }
}
