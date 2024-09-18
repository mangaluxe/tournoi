package org.example.tournoi.controller;


import org.example.tournoi.entity.Message;
import org.example.tournoi.service.AuthService;
import org.example.tournoi.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
            if (message.getId() != null) {
                messageService.updateMessage(message.getId(), message);
                return "redirect:/messages";
            } else {
                message.setDateEnvoi(LocalDateTime.now());
                messageService.saveMessage(message);
                System.out.println("ok");
                return "redirect:/messages";

            }
        }
        return "redirect:/login";
    }

    @RequestMapping("/messages/update")
    public String updateMessage (@RequestParam("messageId") Long id, Model model) {
        if(authService.isLogged()) {
            Message message = messageService.getMessageById(id);
            model.addAttribute("message", message);
            return "/message/updateMessage";
        }
        return "redirect:/login";
    }

    @RequestMapping("/messages/delete")
    public String deleteMessage (@RequestParam("messageId") Long id) {
        if(authService.isLogged()) {
            messageService.deleteMessage(id);
            return "redirect:/messages";
        }
        return "redirect:/login";
    }





}
