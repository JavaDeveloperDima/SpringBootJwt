package com.example.springrestjwt.controllers;

import com.example.springrestjwt.dto.MessageDTO;
import com.example.springrestjwt.model.Message;
import com.example.springrestjwt.model.User;
import com.example.springrestjwt.service.Abstract.MessageService;
import com.example.springrestjwt.service.Abstract.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final MessageService messageService;

    @PostMapping("/sendMessage")
    public ResponseEntity<?> sendMassage(@RequestBody MessageDTO messageDTO){
       return userService.sendMessage(messageDTO);
    }
}
