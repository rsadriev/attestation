package com.example.attestation03_01.uitests;

import com.example.attestation03_01.model.Customer;
import com.example.attestation03_01.model.Stock;
import com.example.attestation03_01.repository.CustomerRepository;
import com.example.attestation03_01.repository.StockRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testTransaction() throws Exception {
        var customer = new Customer();
        customer.setId(1L);
        customer.setName("Test name");
        customer.setSurname("Test surname");
        customer.setBalance(500.0);

        customerRepository.save(customer);

        this.mockMvc.perform(get("/customer/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Test name")));

        var stock = new Stock();
        stock.setId(3L);
        stock.setCount(10L);
        stock.setDescription("Test description");
        stock.setPrice(500.0);

        stockRepository.save(stock);

        this.mockMvc.perform(get("/stock/3")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Test description")));

        this.mockMvc.perform(post("/customer/1/addStock")
                        .param("stockId", String.valueOf(stock.getId()))
                        .param("count", String.valueOf(1)))
                .andExpect(status().is3xxRedirection());

        this.mockMvc.perform(get("/customer/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Test description")));
    }
}
