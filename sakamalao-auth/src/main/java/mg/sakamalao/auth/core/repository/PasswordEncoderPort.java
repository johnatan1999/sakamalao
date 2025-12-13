package mg.sakamalao.auth.core.repository;

public interface PasswordEncoderPort {
    boolean validate(String password, String passwordHash);
    String encode(String password);
}
