package com.example.springrestjwt.service;

import com.example.springrestjwt.model.Message;
import com.example.springrestjwt.model.User;
import com.example.springrestjwt.repositories.MessageRepository;
import com.example.springrestjwt.service.Abstract.UserService;
import com.example.springrestjwt.service.impl.MessageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(SpringExtension.class)
class MessageServiceImplTest {
    private static final String BODY = "sms";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "dima@mail.ru";
    private static final String USER_NAME = "sergei";
    private static final User USER = new User(USER_NAME,EMAIL,PASSWORD);
    private static final Message MESSAGE = new Message(BODY,USER);
    private static final List<String> MESSAGE_LIST = List.of(MESSAGE.getMassage());
    @MockBean
    private MessageRepository messageRepository;
    @MockBean
    private UserService userService;
    private MessageServiceImpl messageService;
    @BeforeEach
    void setUp(){
        messageService = new MessageServiceImpl(messageRepository,userService);
    }

    @Test
    @DisplayName("Save massage")
    void saveMassageTest() {
        when(messageRepository.save(MESSAGE)).thenReturn(MESSAGE);

        Message message = messageService.saveMassage(MESSAGE);

        assertEquals(MESSAGE,message);
        verify(messageRepository).save(MESSAGE);
    }

    @Test
    @DisplayName("Get message list")
    void getMassageTest() {
        when(messageRepository.massageList()).thenReturn(MESSAGE_LIST);

        List<String> list = messageService.getMassage();

        assertEquals(MESSAGE_LIST,list);
        verify(messageRepository).massageList();
    }
}