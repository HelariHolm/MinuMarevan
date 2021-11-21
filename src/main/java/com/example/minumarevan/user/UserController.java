package com.example.minumarevan.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    public UserService service;

    @GetMapping("/register")
    public String showNewForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register/save")
    public String saveUser(User user) {
        service.save(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(User user, @RequestParam String username, @RequestParam String password, RedirectAttributes ra) {
        if (username != null && password != null && !user.getUsername().equals(username) && !user.getPassword().equals(password)) {
            ra.addFlashAttribute("message", "Login information incorrect! Try again.");
        }
        return "redirect:/index";
    }
}
