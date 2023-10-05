package org.jbdev.tnt.controllers;

import org.jbdev.tnt.entities.User;
import org.jbdev.tnt.services.LoggedUserManagementService;
import org.jbdev.tnt.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class UserController {
    private final UserServices userServices;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUserManagementService loggedUserManagementService;

    @Autowired
    public UserController(UserServices userServices,
                          PasswordEncoder passwordEncoder,
                          LoggedUserManagementService loggedUserManagementService) {
        this.userServices = userServices;
        this.passwordEncoder = passwordEncoder;
        this.loggedUserManagementService = loggedUserManagementService;
    }

    @GetMapping("/login")
//    @PreAuthorize("hasAnyRole('admin', 'user')")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout(Principal principal) {
        loggedUserManagementService.setEmail(null);
        return "redirect:/";
    }

    @GetMapping("/user")
    public String signup() {
        return "signup";
    }

    @PostMapping("/user")
    public String postUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userServices.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/user-profile")
    public String getUser(Model model) {
        var user = userServices.userProfile();
        model.addAttribute("me", user);
        return "me";
    }

    @PostMapping("/user-profile")
    public String updateProfile(User user, Model model) {
        userServices.userProfileUpdate(user);
        return "redirect:/login";
    }

    @GetMapping("/user-profile-delete")
    public String deleteUser(){
        userServices.userProfileDelete();
        return "redirect:/";
    }

    @GetMapping("/all-users")
    @PreAuthorize("hasAuthority('admin')")
    public String getAllUsers(Model model) {
        var users = userServices.findAll();
        model.addAttribute("users", users);
        return "allUsers";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('admin')")
    public String admin() {
        return "adminPage";
    }

    @GetMapping("/update/{userID}")
    @PreAuthorize("hasAuthority('admin')")
    public String findByID(Model model, @PathVariable int userID) {
        var user = userServices.findUserById(userID);
        model.addAttribute("user", user);
        return "adminEditUser";
    }

    @PostMapping("/update/{userID}")
    @PreAuthorize("hasAuthority('admin')")
    public String adminUpdate(User user, @PathVariable int userID) {
        userServices.findUserAndUpdate(userID, user);
        return "redirect:/all-users";
    }

    @GetMapping("/delete/{userID}")
    public String adminDelete(@PathVariable int userID) {
        userServices.findUserAndDelete(userID);
        return "redirect:/all-users";
    }
}
