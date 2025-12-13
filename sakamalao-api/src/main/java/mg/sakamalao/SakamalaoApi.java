package mg.sakamalao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "mg.sakamalao")
public class SakamalaoApi {
    public static void main(String[] args) {
        SpringApplication.run(SakamalaoApi.class, args);
    }
}