package com.example.minumarevan.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        return "redirect:/login";
    }

    @GetMapping("/editProfile/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            User user = service.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID = " + id + ")");
            return "user_form";
        } catch (UserNotFoundException ex){
            ra.addFlashAttribute("message", ex.getMessage());
            return "redirect:/home";
        }
    }

    @GetMapping("/perform_login")
    public String login(User user, @RequestParam String username, @RequestParam String password, RedirectAttributes ra) {
        return "redirect:/home";
    }
}
