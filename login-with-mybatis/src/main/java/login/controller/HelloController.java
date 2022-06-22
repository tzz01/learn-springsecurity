package login.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HelloController {
    @GetMapping("/admin/hello")
    public String helloAdmin() {
        return "hello admin.";
    }

    @GetMapping("/user/hello")
    public String helloUser() {
        return "hello user.";
    }

    @GetMapping("/general/hello")
    public String generalHello() {
        return "general hello.";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello.";
    }
}
