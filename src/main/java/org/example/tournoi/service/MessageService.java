package org.example.tournoi.service;

import jakarta.servlet.http.HttpSession;
import org.example.tournoi.dao.MessageRepository;
import org.example.tournoi.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

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