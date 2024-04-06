package com.example.attestation03_01.resttests;

import com.example.attestation03_01.model.Stock;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest
@AutoConfigureMockMvc
public class StockApiTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testStocks() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/stock")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void addStock() throws Exception {
        Stock stock = new Stock();

        stock.setDescription("Test");
        stock.setPrice(200.0);
        stock.setCount(20L);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/stock")
                        .content(mapper.writeValueAsString(stock))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is(stock.getDescription())))
                .andExpect(jsonPath("$.price", is(stock.getPrice())))
                .andExpect(jsonPath("$.count", is(stock.getCount().intValue())));
    }

    @Test
    public void deleteStock() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/stock/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/stock")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
