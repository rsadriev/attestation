package com.example.attestation03_01.uitests;

import com.example.attestation03_01.model.Customer;
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
public class AdminCustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void userView() throws Exception {
        this.mockMvc.perform(get("/customer/add")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Баланс")));
    }

    @Test
    public void addUser() throws Exception {
        var customer = new Customer();
        customer.setId(1L);
        customer.setName("Test name");
        customer.setSurname("Test surname");
        customer.setBalance(500.0);

        this.mockMvc.perform(post("/customer/create")
                        .param("id", String.valueOf(customer.getId()))
                        .param("name", customer.getName())
                        .param("surname", customer.getSurname())
                        .param("balance", String.valueOf(customer.getBalance())))
                .andExpect(status().is3xxRedirection());

        this.mockMvc.perform(get("/customer/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Test name")));
    }

    @Test
    public void editUser() throws Exception {
        var customer = new Customer();
        customer.setId(2L);
        customer.setName("Test name");
        customer.setSurname("Test surname");
        customer.setBalance(500.0);

        this.mockMvc.perform(post("/customer/create")
                        .param("id", String.valueOf(customer.getId()))
                        .param("name", customer.getName())
                        .param("surname", customer.getSurname())
                        .param("balance", String.valueOf(customer.getBalance())))
                .andExpect(status().is3xxRedirection());

        this.mockMvc.perform(get("/customer")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Test name")));

        this.mockMvc.perform(get("/customer/2")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Test name")));

        customer.setName("Test name 2");

        this.mockMvc.perform(post("/customer/create")
                        .param("id", String.valueOf(customer.getId()))
                        .param("name", customer.getName())
                        .param("surname", customer.getSurname())
                        .param("balance", String.valueOf(customer.getBalance())))
                .andExpect(status().is3xxRedirection());

        this.mockMvc.perform(get("/customer/2")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Test name 2")));
    }

    @Test
    public void deleteUser() throws Exception {
        var customer = new Customer();
        customer.setId(3L);
        customer.setName("Test name");
        customer.setSurname("Test surname");
        customer.setBalance(500.0);

        this.mockMvc.perform(post("/customer/create")
                        .param("id", String.valueOf(customer.getId()))
                        .param("name", customer.getName())
                        .param("surname", customer.getSurname())
                        .param("balance", String.valueOf(customer.getBalance())))
                .andExpect(status().is3xxRedirection());

        this.mockMvc.perform(get("/customer/3")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Test name")));

        this.mockMvc.perform(get("/customer/delete/3")).andDo(print()).andExpect(status().is3xxRedirection());
    }
}
