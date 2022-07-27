package com.example.springrestjwt.service.impl;

import com.example.springrestjwt.config.SecurityConfig.jwt.JwtUtil;
import com.example.springrestjwt.dto.AuthenticationDTO;
import com.example.springrestjwt.dto.MessageDTO;
import com.example.springrestjwt.dto.UserDTO;
import com.example.springrestjwt.model.Message;
import com.example.springrestjwt.model.User;
import com.example.springrestjwt.repositories.UserRepository;
import com.example.springrestjwt.service.Abstract.MessageService;
import com.example.springrestjwt.service.Abstract.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UsernamePasswordAuthenticationTokenService authenticationTokenService;
    private final AuthenticationManager authenticationManager;
    private final MessageService messageService;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByUsername(name).orElseThrow(()->
                new UsernameNotFoundException("User с именем "+ name +" не найден"));
    }

    @Override
    public Map<String, String> registration(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        String token = jwtUtil.generateToken(user.getUsername());
        userRepository.save(user);
        return Map.of("jwt-token",token);
    }

    @Override
    public Map<String, String> login(AuthenticationDTO authenticationDTO) {
        try {
            authenticationManager.authenticate(authenticationTokenService.
                    getUserPassAuthToken(authenticationDTO.getUsername(),authenticationDTO.getPassword()));
        } catch (BadCredentialsException e) {
            return Map.of("message", "Incorrect credentials!");
        }

        String token = jwtUtil.generateToken(authenticationDTO.getUsername());
        return Map.of("jwt-token", token);
    }

    @Override
    public ResponseEntity<?> sendMessage(MessageDTO messageDTO) {
        if (messageDTO.getMessage().equals("history 10")){
            return new ResponseEntity<>(messageService.getMassage(), HttpStatus.OK);
        }else {
            Message message = messageService.convert(messageDTO);
            messageService.saveMassage(message);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
}
