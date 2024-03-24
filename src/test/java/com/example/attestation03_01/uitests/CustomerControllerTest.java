package com.example.attestation03_01.uitests;

import com.example.attestation03_01.model.Customer;
import com.example.attestation03_01.repository.CustomerRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testUser() throws Exception {
        var customer = new Customer();
        customer.setId(1L);
        customer.setName("Test name");
        customer.setSurname("Test surname");
        customer.setBalance(500.0);

        customerRepository.save(customer);

        this.mockMvc.perform(get("/customer/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Test name")));
    }
}
