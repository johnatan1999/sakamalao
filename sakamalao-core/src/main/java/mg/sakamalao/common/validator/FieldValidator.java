package mg.sakamalao.common.validator;

import mg.sakamalao.common.core.domain.exception.InvalidFieldException;
import mg.sakamalao.common.core.domain.exception.MissingFieldException;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class FieldValidator {

    private FieldValidator() {}

    public static void nonNegative(String field, Number value) {
        if (value == null) {
            throw new MissingFieldException(field);
        }

        if (value instanceof BigDecimal bd) {
            if (bd.compareTo(BigDecimal.ZERO) < 0) {
                throw new InvalidFieldException("%s can not be negative".formatted(field));
            }
            return;
        }

        if (value.doubleValue() < 0) {
            throw new InvalidFieldException("%s can not be negative".formatted(field));
        }
    }

    /**
     * For non nullable and empty-able field
     * @param field The field name
     * @param value The field value
     */
    public static void required(String field, String value) {
        if (value == null || value.isEmpty()) {
            throw new MissingFieldException(field);
        }
    }

    /**
     * Fro non nullable field
     * @param field The field name
     * @param value The field value
     */
    public static void notNull(String field, Object value) {
        if (value == null) {
            throw new MissingFieldException(field);
        }
    }

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    public static void validateEmail(String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidFieldException("Invalid email format");
        }
    }
}
