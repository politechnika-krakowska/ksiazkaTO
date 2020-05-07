package org.pk.jp.jkkn.controllers;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Test;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void pingTest() throws Exception {
        mockMvc.perform(get("/ping"))
                .andExpect(content().string("pong!"))
                .andExpect(status().isOk());

    }

    @Test
    public void frontendRedirectTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(redirectedUrl("index.html"));

    }

}
