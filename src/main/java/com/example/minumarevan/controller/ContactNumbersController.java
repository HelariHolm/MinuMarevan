package com.example.minumarevan.controller;

import com.example.minumarevan.model.ContactNumbers;
import com.example.minumarevan.security.CustomUserDetails;
import com.example.minumarevan.service.ContactNumbersService;
import com.example.minumarevan.user.User;
import com.example.minumarevan.user.UserNotFoundException;
import com.example.minumarevan.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactNumbersController {

    @Autowired
    public ContactNumbersService contactNumbersService;

    @Autowired
    public UserService userService;

    @PostMapping("/contact-numbers/save")
    public String saveContactNumbers(ContactNumbers numbers, RedirectAttributes ra) throws UserNotFoundException {
        final Integer userId = getLoggedInUserId();
        User user = userService.get(userId);

        ContactNumbers userNumbers = user.getContactNumbers();
        userNumbers.setDoctor(numbers.getDoctor());
        userNumbers.setNextOfKin(numbers.getNextOfKin());

        user.setContactNumbers(userNumbers);

        userService.save(user);

        ra.addFlashAttribute("message", "The User has been saved successfully!");
        return "redirect:/loginSuccess";
    }

    private Integer getLoggedInUserId() throws UserNotFoundException {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        final CustomUserDetails user = (CustomUserDetails) loggedInUser.getPrincipal();
        final String email = user.getEmail();

        User foundUser = userService.findUserByEmail(email);

        return foundUser.getId();
    }

}
