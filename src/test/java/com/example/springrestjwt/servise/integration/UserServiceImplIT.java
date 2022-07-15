package com.example.springrestjwt.servise.integration;

import com.example.springrestjwt.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:applicationTest.properties")
public class UserServiceImplIT {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Статус 403")
    public void registrationTest() throws Exception {
        mockMvc.perform(post("/auth/sendMassage"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Проверка сущуствующего пользователя")
    void enterWithExistingUser() throws Exception {
        mockMvc.perform(post("/api/login").contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON).content("{\"name\": \"dima\", \"password\": \"10\"}"))
                .andExpect(status().isOk());

    }
//    @Test
//    @DisplayName("Проверка на несуществующего пользователя")
//    void enterWithInvalidParametr() throws Exception{
//        mockMvc.perform(post("/api/login").contentType(APPLICATION_JSON)
//                        .accept(APPLICATION_JSON).content("{\"name\": \"non-existent\", \"password\": \"non-existent\"}"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(APPLICATION_JSON))
////                .andExpect();
//    }
}
