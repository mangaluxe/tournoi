package org.example.tournoi.service;

import org.example.tournoi.repository.MessageRepository;
import org.example.tournoi.entity.Message;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MessageService {

    // ========== Propriétés ==========

    private final MessageRepository messageRepository;


    // ========== Constructeur ==========

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }


    // ========== Méthodes ==========

    public void saveMessage(Message message) {
        messageRepository.save(message);
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

    public Message updateMessage(Long id, Message updateMessage) {
        Message messageExist = messageRepository.findById(id).orElse(null);
        if (messageExist != null) {
            messageExist.setId(messageExist.getId());
            messageExist.setTitre(updateMessage.getTitre());
            messageExist.setContenu(updateMessage.getContenu());
            saveMessage(messageExist);
        }
        return null;
    }

}