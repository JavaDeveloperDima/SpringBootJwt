package com.example.springrestjwt.service.impl;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UsernamePasswordAuthenticationTokenService {
    public UsernamePasswordAuthenticationToken getUserPassAuthToken(String name,String password){
        return new UsernamePasswordAuthenticationToken(name,password);
    }
}
