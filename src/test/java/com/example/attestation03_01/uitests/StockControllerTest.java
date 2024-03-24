package com.example.attestation03_01.uitests;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.attestation03_01.model.Stock;
import com.example.attestation03_01.repository.StockRepository;
import org.junit.Test;
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
public class StockControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StockRepository stockRepository;

    @Test
    public void testStock() throws Exception {
        var stock = new Stock();
        stock.setId(1L);
        stock.setCount(10L);
        stock.setDescription("Test description");
        stock.setPrice(500.0);

        stockRepository.save(stock);

        this.mockMvc.perform(get("/stock/3")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Test description")));
    }
}
