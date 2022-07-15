package com.example.springrestjwt.service;

import com.example.springrestjwt.config.SecurityConfig.jwt.JwtUtil;
import com.example.springrestjwt.dto.AuthenticationDTO;
import com.example.springrestjwt.dto.UserDTO;
import com.example.springrestjwt.model.User;
import com.example.springrestjwt.repositories.UserRepository;
import com.example.springrestjwt.service.impl.UserServiceImpl;
import com.example.springrestjwt.service.impl.UsernamePasswordAuthenticationTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    private static final String PASSWORD = "password";
    private static final String EMAIL = "dima@mail.ru";
    private static final String USER_NAME = "sergei";
    private static final String TOKEN = "token";
    private static final String WRONG_NAME = "wrong name";
    private static final String WRONG_PASSWORD = "wrong password";
    private static final Map<String,String> EXPECTED_MAP = Map.of("jwt-token",TOKEN);
    private static final Map<String,String> EXCEPTION_MAP = Map.of("message", "Incorrect credentials!");
    private static final User USER = new User(USER_NAME,EMAIL,PASSWORD);
    private static final UserDTO USER_DTO = new UserDTO(USER_NAME,PASSWORD,EMAIL);
    private static final AuthenticationDTO WRONG_AUTH_DTO = new AuthenticationDTO(WRONG_NAME,WRONG_PASSWORD);
    private static final AuthenticationDTO AUTHENTICATION_DTO = new AuthenticationDTO(USER_NAME,PASSWORD);
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private JwtUtil jwtUtil;
    @MockBean
    private AuthenticationManager authenticationManager;
    private UserServiceImpl userServiceImpl;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private UsernamePasswordAuthenticationTokenService authenticationTokenService;

    @BeforeEach
    void setUp(){
        userServiceImpl = new UserServiceImpl(userRepository,passwordEncoder,jwtUtil
                ,authenticationTokenService,authenticationManager);
    }

    @Test
    @DisplayName("Find user")
    void findByNameTest() {
        when(userRepository.findByUsername(USER.getUsername())).thenReturn(Optional.of(USER));

        User result = userServiceImpl.findByName(USER.getUsername());

        assertEquals(USER,result);
        verify(userRepository).findByUsername(USER.getUsername());
    }

    @Test
    @DisplayName("User not found")
    void findByNameIfNotFoundTest()  {
        when(userRepository.findByUsername(USER.getUsername())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class,
                ()->userServiceImpl.findByName(USER.getUsername()));
    }

    @Test
    @DisplayName("User registration")
    void registrationTest() throws Exception{
        when(passwordEncoder.encode(USER_DTO.getPassword())).thenReturn(PASSWORD);
        when(jwtUtil.generateToken(USER_NAME)).thenReturn(TOKEN);

        Map<String,String> map = userServiceImpl.registration(USER_DTO);

        assertEquals(EXPECTED_MAP,map);
        verify(passwordEncoder).encode(USER_DTO.getPassword());
        verify(jwtUtil).generateToken(USER_NAME);
        verify(userRepository).save(USER);
    }

    @Test
    @DisplayName("Login with correct credentials")
    void loginTest(){
        when(jwtUtil.generateToken(AUTHENTICATION_DTO.getUsername())).thenReturn(TOKEN);

        Map<String,String> map = userServiceImpl.login(AUTHENTICATION_DTO);

        assertEquals(EXPECTED_MAP,map);
        verify(authenticationManager).authenticate(authenticationTokenService
                .getUserPassAuthToken(AUTHENTICATION_DTO.getUsername(),AUTHENTICATION_DTO.getPassword()));
        verify(jwtUtil).generateToken(AUTHENTICATION_DTO.getUsername());
    }

    @Test
    @DisplayName("Login with invalid credentials")
    void invalidLoginTest(){
        when(authenticationManager.authenticate(authenticationTokenService.
                getUserPassAuthToken(WRONG_AUTH_DTO.getUsername(),WRONG_AUTH_DTO.getPassword())))
                .thenThrow(BadCredentialsException.class);

        Map<String,String> map = userServiceImpl.login(WRONG_AUTH_DTO);

        assertEquals(EXCEPTION_MAP,map);
    }
}
