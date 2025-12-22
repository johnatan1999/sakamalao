package mg.sakamalao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "mg.sakamalao.auth",
        "mg.sakamalao.api",
        "mg.sakamalao.project",
        "mg.sakamalao.expense",
        "mg.sakamalao.income",
        "mg.sakamalao.dashboard",
        "mg.sakamalao.common",
        "mg.sakamalao.cms"
})
public class SakamalaoApi {
    public static void main(String[] args) {
        SpringApplication.run(SakamalaoApi.class, args);
    }
}