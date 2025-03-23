package com.sparta.fbpractice.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Tip : @RestController = @Controller + @ResponseBody
@CrossOrigin(origins = "http://localhost:3000")
public class HelloWorldController {

    @GetMapping("/api/hello")
    public String test() {
        return "Hello, world!";
    }
}
