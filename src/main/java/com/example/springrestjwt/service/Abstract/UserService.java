package com.example.springrestjwt.service.Abstract;

import com.example.springrestjwt.dto.AuthenticationDTO;
import com.example.springrestjwt.dto.UserDTO;
import com.example.springrestjwt.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public List<User> getAll();
    public User findByName(String name);
    public Map<String,String> registration(UserDTO userDTO);
    public Map<String,String> login(AuthenticationDTO authenticationDTO);

}
