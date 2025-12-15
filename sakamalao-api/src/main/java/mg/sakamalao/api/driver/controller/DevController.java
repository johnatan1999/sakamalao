package mg.sakamalao.api.driver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DevController {

    @GetMapping
    public String test() {
        return "This is not a test!";
    }
}
