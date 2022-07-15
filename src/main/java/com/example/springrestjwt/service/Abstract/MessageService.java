package com.example.springrestjwt.service.Abstract;

import com.example.springrestjwt.dto.MessageDTO;
import com.example.springrestjwt.model.Message;

import java.util.List;

public interface MessageService {
    public Message saveMassage(Message message);
    public List<String> getMassage();
    public Message convert(MessageDTO messageDTO);
}
