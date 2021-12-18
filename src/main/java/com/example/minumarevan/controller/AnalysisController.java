package com.example.minumarevan.controller;

import com.example.minumarevan.model.Analysis;
import com.example.minumarevan.model.ContactNumbers;
import com.example.minumarevan.security.CustomUserDetails;
import com.example.minumarevan.service.AnalysisService;
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

import java.util.Set;

@Controller
public class AnalysisController {

    @Autowired
    public AnalysisService analysisService;

    @Autowired
    public UserService userService;

    @PostMapping("/analysis/save")
    public String saveContactNumbers(Analysis analysis, RedirectAttributes ra) throws UserNotFoundException {
        final Integer userId = getLoggedInUserId();
        User user = userService.get(userId);

        analysis.setUser(user);
        analysisService.save(analysis);

        ra.addFlashAttribute("message", "The Analysis has been saved successfully!");
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
