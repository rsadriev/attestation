package com.example.attestation03_01.resttests;

import com.example.attestation03_01.model.Customer;
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
public class CustomerApiTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testCustomers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/customer")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void addCustomer() throws Exception {
        Customer customer = new Customer();

        customer.setName("Test");
        customer.setSurname("Tesst");
        customer.setBalance(4000.0);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/customer")
                        .content(mapper.writeValueAsString(customer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(customer.getName())))
                .andExpect(jsonPath("$.surname", is(customer.getSurname())))
                .andExpect(jsonPath("$.balance", is(customer.getBalance())));
    }

    @Test
    public void modifyCustomer() throws Exception {
        Customer customer = new Customer();

        customer.setId(1L);
        customer.setName("Test_mod");
        customer.setSurname("Tesst");
        customer.setBalance(5000.0);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/customer")
                        .content(mapper.writeValueAsString(customer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/customer")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(customer.getName())))
                .andExpect(jsonPath("$[0].surname", is(customer.getSurname())))
                .andExpect(jsonPath("$[0].balance", is(customer.getBalance())));
    }

    @Test
    public void deleteCustomer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/customer/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
