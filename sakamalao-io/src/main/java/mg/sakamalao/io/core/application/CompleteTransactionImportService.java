package mg.sakamalao.io.core.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mg.sakamalao.io.core.domain.input.MappedTransactionInput;
import mg.sakamalao.io.core.usecase.CompleteImportTransactionUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompleteTransactionImportService {
    private final CompleteImportTransactionUseCase completeImportTransactionUseCase;

    @Transactional
    public void completeImportTransaction(
            UUID userId,
            UUID projectId,
            UUID sessionId,
            List<MappedTransactionInput> transactions
    ) {
        this.completeImportTransactionUseCase.completeTransactionsImport(
                userId,
                projectId,
                sessionId,
                transactions
        );
    }
}
