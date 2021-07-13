package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.integration;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.AccountRepository;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.util.TestUtilsGenerator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext( classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BackofficeTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    String token;

    @BeforeAll
    void setup(){
        token = TestUtilsGenerator.createToken();
    }

    @Test
    void testListClients() throws Exception {
        accountRepository.saveAll(TestUtilsGenerator.createClients());
        
        this.mockMvc.perform(
            get("/backoffice/list_accounts")
                    .header("Authorization", token))
            .andDo(print()).andExpect(status().isOk())
            .andExpect(jsonPath("$.[0].userName").value("lucas"));

    }
}
