package org.example.tournoi.service;

import jakarta.servlet.http.HttpSession;
import org.example.tournoi.dao.MessageRepository;
import org.example.tournoi.dao.UtilisateurRepository;
import org.example.tournoi.entity.Message;
import org.example.tournoi.entity.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    HttpSession httpSession;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageById(Long id) {
        return messageRepository.findById(id).orElse(null);
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }

}