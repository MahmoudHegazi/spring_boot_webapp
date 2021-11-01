package com.project.javausers.controller;

import com.project.javausers.model.User;
import com.project.javausers.service.MessageListService;
import com.project.javausers.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private MessageListService messageListService;
    private UserService userService;

    public HomeController(MessageListService messageListService, UserService userService) {
        this.messageListService = messageListService;
        this.userService = userService;
    }

    @GetMapping
    public String getHomePage(Authentication auth, Model model) {
        // get new object and use auth.getName to get the username of logged user and use userserivce to return that user
        User Logged = userService.getUser(auth.getName());
        //model.addAttribute("greetings", this.messageListService.getMessages());
        model.addAttribute("messages", messageListService.getMessages());
        model.addAttribute("auth", auth.getName());
        model.addAttribute("details", Logged.getLastName());
        model.addAttribute("user", Logged);

        return "home";
    }


    @PostMapping
    public String addMessage(Authentication auth, Model model) {
        User Logged = userService.getUser(auth.getName());
        model.addAttribute("messages", messageListService.getMessages());
        model.addAttribute("auth", auth.getName());
        model.addAttribute("details", Logged.getLastName());
        model.addAttribute("user", Logged);
        //model.addAttribute("greetings", messageListService.getMessages());
        return "home";
    }

}
