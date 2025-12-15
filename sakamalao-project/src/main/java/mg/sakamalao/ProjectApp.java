package mg.sakamalao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "mg.sakamalao.project")
public class ProjectApp {
    public static void main(String[] args) {
        SpringApplication.run(ProjectApp.class);
    }
}