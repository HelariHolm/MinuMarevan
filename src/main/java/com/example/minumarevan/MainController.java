package com.example.minumarevan;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String showStartPage() {
        return "index";
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }

}
