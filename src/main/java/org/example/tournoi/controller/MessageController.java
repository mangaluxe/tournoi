package org.example.tournoi.controller;

import org.example.tournoi.entity.Message;
import org.example.tournoi.service.AuthService;
import org.example.tournoi.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@Controller
public class MessageController {

    // ========== Propriétés ==========

    private final MessageService messageService;
    private final AuthService authService;


    // ========== Constructeur ==========

    public MessageController(MessageService messageService, AuthService authService) {
        this.messageService = messageService;
        this.authService = authService;
    }


    // ========== Méthodes ==========

    // ----- Read -----

    @RequestMapping("/messages")
    public String listMessages (Model model) {

        List<Message> messages = messageService.getAllMessages();

        // Formater les dates pour chaque tournoi
        messages.forEach(t -> t.formatDates());

        if (authService.isLogged()) {

            model.addAttribute("title", "Messages"); // Pour le title de la page

            model.addAttribute("messages", messages);

            return "message/index-message";
        }

        return "redirect:/login";
    }


    // ----- Create -----

    @RequestMapping("/messages/create")
    public String createMessage (Model model) {

        if (authService.isLogged()) {

            model.addAttribute("message", new Message());

            model.addAttribute("title", "Créer un messages"); // Pour le title de la page

            return "message/create-message";
        }
        return "redirect:/login";
    }

    @PostMapping("messages/add")
    public String addMessage (@ModelAttribute("message") Message message) {
        if (authService.isLogged()) {
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


    // ----- Update -----

    @RequestMapping("/messages/update")
    public String updateMessage (@RequestParam("messageId") Long id, Model model) {
        if (authService.isLogged()) {

            Message message = messageService.getMessageById(id);

            model.addAttribute("message", message);

            model.addAttribute("title", "Modifier un message"); // Pour le title de la page

            return "message/update-message";
        }
        return "redirect:/login";
    }


    // ----- Delete -----

    @RequestMapping("/messages/delete")
    public String deleteMessage (@RequestParam("messageId") Long id) {
        if (authService.isLogged()) {
            messageService.deleteMessage(id);
            return "redirect:/messages";
        }
        return "redirect:/login";
    }


}
