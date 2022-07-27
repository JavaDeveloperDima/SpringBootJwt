package com.example.springrestjwt.controllers;

import com.example.springrestjwt.config.SecurityConfig.jwt.JwtUtil;
import com.example.springrestjwt.dto.AuthenticationDTO;
import com.example.springrestjwt.dto.UserDTO;
import com.example.springrestjwt.service.Abstract.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/registration")
    public Map<String,String> registration(@RequestBody @Valid UserDTO userDto){
       return userService.registration(userDto);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody AuthenticationDTO authenticationDTO) {
       return userService.login(authenticationDTO);
    }
}
