package mg.sakamalao.core.domain.exception;

public class MissingFieldException extends RuntimeException {
    public MissingFieldException(String entity, String attribute) {
        super("Missing required attribute '" + attribute + "' for entity " + entity);
    }

    public MissingFieldException(String attribute) {
        super("Missing required attribute: " + attribute);
    }
}
