package mg.sakamalao.api.driver.handler;

import lombok.extern.slf4j.Slf4j;
import mg.sakamalao.common.core.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    public record ErrorResponse(String code, String message, LocalDateTime timestamp) {}

    @ExceptionHandler(MissingFieldException.class)
    public ResponseEntity<ErrorResponse> handleMissingField(MissingFieldException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                buildErrorResponse("MISSING_FIELD", ex)
        );
    }

    @ExceptionHandler(InvalidFieldException.class)
    public ResponseEntity<ErrorResponse> handleInvalidField(InvalidFieldException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                buildErrorResponse("INVALID_FIELD", ex)
        );
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                buildErrorResponse("USER_ALREADY_EXISTS", ex)
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                buildErrorResponse("ENTITY_NOT_FOUND", ex)
        );
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthentication(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                buildErrorResponse("AUTH_FAILED", ex)
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                buildErrorResponse("INTERNAL_ERROR", ex)
        );
    }

    private ErrorResponse buildErrorResponse(String code, Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ErrorResponse(
                code,
                ex.getMessage(),
                LocalDateTime.now()
        );
    }
}
