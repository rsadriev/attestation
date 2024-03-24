package com.example.attestation03_01.uitests;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.attestation03_01.model.Stock;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdminStockControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void stockView() throws Exception {
        this.mockMvc.perform(get("/stock/add")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Количество")));
    }

    @Test
    @Order(2)
    public void addStock() throws Exception {
        var stock = new Stock();

        stock.setId(0L);
        stock.setDescription("Test description");
        stock.setPrice(10.0);
        stock.setCount(10L);

        this.mockMvc.perform(post("/stock/create")
                        .param("id", String.valueOf(stock.getId()))
                        .param("count", String.valueOf(stock.getCount()))
                        .param("description", stock.getDescription())
                        .param("price", String.valueOf(stock.getPrice())))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @Order(3)
    public void testStocks() throws Exception {
        this.mockMvc.perform(get("/stock")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Test description")));
    }

    @Test
    @Order(4)
    public void editStock() throws Exception {
        var stock = new Stock();

        stock.setId(1L);
        stock.setDescription("Test description 2");
        stock.setPrice(10.0);
        stock.setCount(10L);

        this.mockMvc.perform(post("/stock/create")
                        .param("id", String.valueOf(stock.getId()))
                        .param("count", String.valueOf(stock.getCount()))
                        .param("description", stock.getDescription())
                        .param("price", String.valueOf(stock.getPrice())))
                .andExpect(status().is3xxRedirection());

        this.mockMvc.perform(get("/stock")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Test description 2")));
    }

    @Test
    @Order(5)
    public void deleteStock() throws Exception {
        this.mockMvc.perform(get("/stock/delete/1")).andDo(print()).andExpect(status().is3xxRedirection());
    }
}
