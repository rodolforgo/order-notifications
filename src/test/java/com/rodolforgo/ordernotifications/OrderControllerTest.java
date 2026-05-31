package com.rodolforgo.ordernotifications;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(KafkaExtension.class)
class OrderControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void syncDeveRetornar201EAplicarStatusPadrao() throws Exception {
        mockMvc.perform(post("/orders/sync")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        [
                          { "id": "abc123", "item": "Milho" },
                          { "id": "def456", "item": "Canjica", "status": "PROCESSING" }
                        ]
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$[0].status").value("CREATED"))
                .andExpect(jsonPath("$[1].status").value("PROCESSING"));
    }

    @Test
    void asyncDeveRetornar202EAplicarStatusPadrao() throws Exception {
        mockMvc.perform(post("/orders/async")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        [
                          { "id": "abc123", "item": "Milho" }
                        ]
                        """))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$[0].status").value("CREATED"));
    }
}
