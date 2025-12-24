package mg.sakamalao.auth.infrastructure.adapter.security;

import jakarta.servlet.http.HttpServletRequest;
import mg.sakamalao.auth.core.domain.security.PublicEndpoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;

@Component
public final class PublicEndpointChecker {
    private final List<RequestMatcher> publicMatchers;

    public PublicEndpointChecker(RequestMappingHandlerMapping handlerMapping) {
        publicMatchers = new ArrayList<>();
        handlerMapping.getHandlerMethods().forEach((requestMappingInfo, handlerMethod) -> {
            // Vérifier si la méthode ou la classe est annotée @PublicEndpoint
            if (handlerMethod.getMethodAnnotation(PublicEndpoint.class) != null ||
                    handlerMethod.getBeanType().getAnnotation(PublicEndpoint.class) != null) {

                // Spring < 6 ou si PatternsCondition existe
                if (requestMappingInfo.getPatternsCondition() != null) {
                    requestMappingInfo.getPatternsCondition().getPatterns()
                            .forEach(pattern -> publicMatchers.add(new AntPathRequestMatcher(pattern)));
                }

                // Spring 6 ou PathPattern-based matching
                if (requestMappingInfo.getPathPatternsCondition() != null) {
                    requestMappingInfo.getPathPatternsCondition().getPatterns()
                            .forEach(pathPattern -> publicMatchers.add(new AntPathRequestMatcher(pathPattern.getPatternString())));
                }
            }
        });
    }

    public boolean isPublicEndpoint(HttpServletRequest request) {
        return publicMatchers.stream()
                .anyMatch(matcher -> matcher.matches(request));
    }
}
