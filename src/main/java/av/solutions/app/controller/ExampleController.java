package av.solutions.app.controller;

import av.solutions.app.entity.ExampleEntity;
import av.solutions.app.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExampleController {
    @Autowired
    private ExampleService exampleService;

    @GetMapping("example")
    public List<ExampleEntity> findImages() {
        return exampleService.getAllExamples();
    }

    @GetMapping("hello-world")
    public String helloWorld() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return "Hello World " + username;
    }

    @GetMapping("hello-world-free")
    public String helloWorld2() {
        return "Hello World free";
    }
}
