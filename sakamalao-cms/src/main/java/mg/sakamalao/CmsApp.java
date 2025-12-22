package mg.sakamalao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "mg.sakamalao.common",
                "mg.sakamalao.project",
                "mg.sakamalao.cms"
        }
)
public class CmsApp {
    public static void main(String[] args) {
        SpringApplication.run(CmsApp.class, args);
    }
}
