package org.example.tournoi.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.tournoi.entity.Message;
import org.example.tournoi.service.AuthService;
import org.example.tournoi.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MessageController {
    private final MessageService messageService;
    private final AuthService authService;

    public MessageController(MessageService messageService, AuthService authService) {
        this.messageService = messageService;
        this.authService = authService;
    }

    @RequestMapping("/messages")
    public String listMessages (Model model) {
        if(authService.isLogged()) {
            model.addAttribute("messages", messageService.getAllMessages());
            return "/message/indexMessage";
        }
        return "redirect:/login";
    }

    @RequestMapping("/messages/create")
    public String createMessage (Model model) {
        if(authService.isLogged()) {
            model.addAttribute("message", new Message());
            return "/message/createMessage";
        }
        return "redirect:/login";
    }
    @PostMapping("messages/add")
    public String addMessage (@ModelAttribute("message") Message message) {
        if(authService.isLogged()) {
            message.setDateEnvoi(LocalDateTime.now());
            messageService.createMessage(message);
        }
        return "redirect:/messages";
    }
}
