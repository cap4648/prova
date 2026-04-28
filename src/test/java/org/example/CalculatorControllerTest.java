package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CalculatorController.class)
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testMultiplyByTwo() throws Exception {
        mockMvc.perform(get("/api/calculator/multiply")
                .param("number", "5"))
                .andExpect(status().isOk())
                .andExpect(content().string("10"));
    }

    @Test
    public void testMultiplyByTwoWithZero() throws Exception {
        mockMvc.perform(get("/api/calculator/multiply")
                .param("number", "0"))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

    @Test
    public void testMultiplyByTwoWithNegative() throws Exception {
        mockMvc.perform(get("/api/calculator/multiply")
                .param("number", "-3"))
                .andExpect(status().isOk())
                .andExpect(content().string("-6"));
    }
}
