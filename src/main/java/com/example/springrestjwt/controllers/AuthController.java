package com.example.springrestjwt.controllers;

import com.example.springrestjwt.dto.MessageDTO;
import com.example.springrestjwt.model.Message;
import com.example.springrestjwt.model.User;
import com.example.springrestjwt.service.Abstract.MessageService;
import com.example.springrestjwt.service.Abstract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final MessageService messageService;

    @Autowired
    public AuthController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<?> sendMassage(@RequestBody MessageDTO messageDTO){
        if (messageDTO.getMessage().equals("history 10")){
            return new ResponseEntity<>(messageService.getMassage(), HttpStatus.OK);
        }else {
            Message message = messageService.convert(messageDTO);
            messageService.saveMassage(message);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
}
