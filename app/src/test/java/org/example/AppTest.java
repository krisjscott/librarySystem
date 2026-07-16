package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AppTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenGetAllBooks_thenStatus200() throws Exception {
        mockMvc.perform(get("/api/books"))
               .andExpect(status().isOk());
    }

    @Test
    void whenGetBookByInvalidId_thenStatus404() throws Exception {
        mockMvc.perform(get("/api/books/999"))
               .andExpect(status().isNotFound());
    }

    @Test
    void whenGetAllUsers_thenStatus200() throws Exception {
        mockMvc.perform(get("/api/user"))
                .andExpect(status().isOk());
    }

    @Test
    void whenGetUserByInvalid_thenStatus404() throws Exception {
        mockMvc.perform(get("/api/user/999"))
                .andExpect(status().isNotFound());
    }
}
