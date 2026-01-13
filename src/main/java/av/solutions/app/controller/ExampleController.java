package av.solutions.app.controller;

import av.solutions.app.entity.ExampleEntity;
import av.solutions.app.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return "Hello World xico gay";
    }
}
