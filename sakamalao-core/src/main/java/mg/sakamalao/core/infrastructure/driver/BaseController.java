package mg.sakamalao.core.infrastructure.driver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import mg.sakamalao.core.domain.entity.User;

public abstract class BaseController {
    protected User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal() == null) {
            return null; // or throw exception if you want
        }

        return (User) auth.getPrincipal();
    }
}
