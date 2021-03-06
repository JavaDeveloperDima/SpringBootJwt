package com.example.springrestjwt.service.impl;

import com.example.springrestjwt.dto.MessageDTO;
import com.example.springrestjwt.model.Message;
import com.example.springrestjwt.model.User;
import com.example.springrestjwt.repositories.MessageRepository;
import com.example.springrestjwt.service.Abstract.MessageService;
import com.example.springrestjwt.service.Abstract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final UserService userService;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository,UserService userService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
    }

    @Override
    public Message saveMassage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<String> getMassage() {
        return messageRepository.massageList();
    }

    @Override
    public Message convert(MessageDTO messageDTO) {
        User user = userService.findByName(messageDTO.getUsername());
        Message message = new Message();
        message.setMassage(messageDTO.getMessage());
        message.setUser(user);
        return message;
    }
}
