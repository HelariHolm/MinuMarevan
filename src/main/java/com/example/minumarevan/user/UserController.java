package com.example.minumarevan.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String saveUser(User user, RedirectAttributes ra) {
        service.save(user);
        ra.addFlashAttribute("message", "The User has been saved successfully!");
        return "redirect:/login";
    }

    @GetMapping("/loginSuccess")
    public String currentUser(Model model) throws UserNotFoundException {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();

        User user = service.findUserByUsername(username);
        model.addAttribute("user", user);

        return "home";
    }

    @PostMapping("/editProfile/{id}")
    public String updateUserProfile(@PathVariable Integer id, User user) {
        try {
            User userToSave = service.get(id);
            userToSave.setFirstname(user.getFirstname());
            userToSave.setLastname(user.getLastname());
            userToSave.setEmail(user.getEmail());
            userToSave.setPassword(user.getPassword());
            userToSave.setUsername(user.getUsername());
            service.save(user);
            return "redirect:/home";
        } catch (UserNotFoundException ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }

    }

}
