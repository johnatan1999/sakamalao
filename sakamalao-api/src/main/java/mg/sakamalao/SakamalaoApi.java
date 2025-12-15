package mg.sakamalao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "mg.sakamalao.auth",
        "mg.sakamalao.api",
        "mg.sakamalao.project",
        "mg.sakamalao.common"
})
public class SakamalaoApi {
    public static void main(String[] args) {
        SpringApplication.run(SakamalaoApi.class, args);
    }
}