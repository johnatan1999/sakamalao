package mg.sakamalao.core.validator;

import mg.sakamalao.core.domain.exception.InvalidFieldException;
import mg.sakamalao.core.domain.exception.MissingFieldException;

public class FieldValidator {
    public static void required(String field, String value) {
        if (value == null || value.isEmpty()) {
            throw new MissingFieldException(field);
        }
    }

    public static void validateEmail(String email) {
        boolean matches = email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
        if (!matches) {
            throw new InvalidFieldException("Invalid email format");
        }
    }
}
