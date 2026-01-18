package mg.sakamalao.common.infrastructure.adapter.config;

import mg.sakamalao.common.core.port.ProjectAccessPort;
import mg.sakamalao.common.core.usecase.ProjectAccessCheckerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {

    @Bean
    public ProjectAccessCheckerUseCase projectAccessCheckerUseCase(
            ProjectAccessPort projectAccessPort
    ) {
        return new ProjectAccessCheckerUseCase(projectAccessPort);
    }
}
