package mg.sakamalao.transaction.core.application;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mg.sakamalao.transaction.core.domain.input.UpdateTransactionInput;
import mg.sakamalao.transaction.core.usecase.UpdateTransactionUseCase;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateTransactionApplicationService {
    private final UpdateTransactionUseCase updateTransactionUseCase;

    @Transactional
    public void update(
            UUID userId,
            UUID transactionId,
            UpdateTransactionInput input
    ) {
        updateTransactionUseCase.update(
                userId,
                transactionId,
                input
        );
    }
}
