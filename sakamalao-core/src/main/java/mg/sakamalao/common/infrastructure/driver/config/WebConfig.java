package mg.sakamalao.common.infrastructure.driver.config;

import lombok.RequiredArgsConstructor;
import mg.sakamalao.common.infrastructure.driver.resolver.CurrentUserArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final CurrentUserArgumentResolver resolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(resolver);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")               // toutes les routes
                .allowedOrigins("*")             // tous les domaines
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS") // toutes les méthodes
                .allowedHeaders("*")             // tous les headers
                .allowCredentials(false)         // true si cookies / auth nécessaire
                .maxAge(3600);                   // cache pré-vol
    }
}
