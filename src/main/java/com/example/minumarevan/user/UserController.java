package com.example.minumarevan.user;

import com.example.minumarevan.model.Analysis;
import com.example.minumarevan.model.ContactNumbers;
import com.example.minumarevan.model.UserRegisterRequest;
import com.example.minumarevan.service.ContactNumbersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    public UserService service;

    @Autowired
    public ContactNumbersService contactNumbersService;

    @GetMapping("/register")
    public String showNewForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register/save")
    public String saveUser(UserRegisterRequest request, RedirectAttributes ra) {
        final User user = new User();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());

        ContactNumbers numbers = new ContactNumbers();
        numbers.setDoctor(request.getDoctor());
        numbers.setNextOfKin(request.getNextOfKin());

        user.setContactNumbers(numbers);

        service.save(user);
        ra.addFlashAttribute("message", "The User has been saved successfully!");
        return "redirect:/login";
    }

    @GetMapping("/loginSuccess")
    public String currentUser(Model model) throws UserNotFoundException {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();

        User user = service.findUserByUsername(username);
        // TODO: null check
        ContactNumbers numbers = user.getContactNumbers();
        Set<Analysis> analyses = user.getAnalyses();
        List<Analysis> analysesSorted = analyses.stream().sorted(Comparator.comparing(Analysis::getInrDate).reversed()).collect(Collectors.toList());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        int day = date.getDay();
        float pills = 0;
        if (day == 1) {
            pills = analysesSorted.get(0).getMondayPills();
        } else if (day == 2) {
            pills = analysesSorted.get(0).getTuesdayPills();
        } else if (day == 3) {
            pills = analysesSorted.get(0).getWednesdayPills();
        } else if (day == 4) {
            pills = analysesSorted.get(0).getThursdayPills();
        } else if (day == 5) {
            pills = analysesSorted.get(0).getFridayPills();
        } else if (day == 6) {
            pills = analysesSorted.get(0).getSaturdayPills();
        } else {
            pills = analysesSorted.get(0).getSundayPills();
        }

        model.addAttribute("user", user);
        model.addAttribute("numbers", numbers);
        model.addAttribute("analyses", analysesSorted);
        model.addAttribute("pills", pills);
        model.addAttribute("today", formatter.format(date));

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
            userToSave.setContactNumbers(user.getContactNumbers());
            service.save(user);
            return "redirect:/loginSuccess";
        } catch (UserNotFoundException ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }

    }

}
