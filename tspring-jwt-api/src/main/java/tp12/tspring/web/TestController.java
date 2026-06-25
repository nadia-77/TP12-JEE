package tp12.tspring.web;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @GetMapping("/api/user/hello")
    public String userHello() {
        return "Bonjour USER !";
    }

    @GetMapping("/api/admin/hello")
    public String adminHello() {
        return "Bonjour ADMIN !";
    }
    @GetMapping("/api/user/profile")
    public String userProfile() {
        return "Profil utilisateur protégé !";
    }
}