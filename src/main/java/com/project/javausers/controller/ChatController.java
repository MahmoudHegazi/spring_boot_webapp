package com.project.javausers.controller;

import com.project.javausers.model.MessageForm;
import com.project.javausers.model.User;
import com.project.javausers.service.MessageListService;
import com.project.javausers.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/chat")
@Controller
public class ChatController {

    private MessageListService messageListService;
    private UserService userService;

    public ChatController(MessageListService messageListService, UserService userService) {
        this.messageListService = messageListService;
        this.userService = userService;
    }

    @GetMapping
    public String getChat(@ModelAttribute("newMessage") MessageForm newMessage, Model model, Authentication auth){
        User logged = userService.getUser(auth.getName());
        model.addAttribute("messages", messageListService.getMessages());
        model.addAttribute("user", logged);
        return "chat";
    }

    @PostMapping
    public String postChat(@ModelAttribute("newMessage") MessageForm newMessage, Model model, Authentication auth){
        User logged = userService.getUser(auth.getName());
        newMessage.setUsername(auth.getName());
        messageListService.addMessage(newMessage);
        model.addAttribute("messages", messageListService.getMessages());
        model.addAttribute("user", logged);
        newMessage.setText("");
        return "chat";
    }

    @ModelAttribute("typeOptions")
    public String[] typeOptions(){ return new String[]{"Whisper", "Shout", "Normal"};}
}
