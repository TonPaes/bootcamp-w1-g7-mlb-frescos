package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.integration;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.UpdateInboundOrderDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.InboundOrderRepository;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.security.JWTUtil;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.util.TestUniUtilsGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InboundOrderServiceImplIntegrationTest extends IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InboundOrderRepository inboundOrderRepository;

    String token;

    @BeforeAll
    void setup() {
        token = TestUniUtilsGenerator.createToken();
    }


    @Test
    void createInboundOrderWithOneBatch() throws Exception {
        String request = TestUniUtilsGenerator.createRequestOneBatch();

        this.mockMvc.perform(
                post("/inboundorder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(request))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.batchStock.[0].currentTemperature").value(10.0))
                .andExpect(jsonPath("$.batchStock.[0].minimumTemperature").value(5.0))
                .andExpect(jsonPath("$.batchStock.[0].initialQuantity").value(500));

    }

    @Test
    void createInboundOrderWithTwoBatch() throws Exception {
        String request = TestUniUtilsGenerator.createRequestTwoBatches();
        this.mockMvc.perform(
                post("/inboundorder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(request))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.batchStock.[0].currentTemperature").value(10.0))
                .andExpect(jsonPath("$.batchStock.[0].minimumTemperature").value(5.0))
                .andExpect(jsonPath("$.batchStock.[1].currentTemperature").value(10.0))
                .andExpect(jsonPath("$.batchStock.[1].minimumTemperature").value(5.0));

    }

    @Test
    void updateInboundOrderWithOneBatch() throws Exception {
        inboundOrderRepository.save(TestUniUtilsGenerator.createOneBatchInboundOrderToPersist());
        UpdateInboundOrderDTO updateInboundOrderDTO = TestUniUtilsGenerator.createUpdateInboundOrderDTO();


        String request = TestUniUtilsGenerator.updateRequestOneBatch();
        this.mockMvc.perform(
                put("/inboundorder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(request))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.batchStock.[0].currentTemperature").value(10.0))
                .andExpect(jsonPath("$.batchStock.[0].minimumTemperature").value(5.0));

    }

    @Test
    void updateInboundOrderWithTwoBatches() throws Exception {
        inboundOrderRepository.save(TestUniUtilsGenerator.createTwoBatchInboundOrderToPersist());


        String request = TestUniUtilsGenerator.updateRequestTwoBatches();
        this.mockMvc.perform(
                put("/inboundorder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", token)
                        .content(request))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.batchStock.[0].currentTemperature").value(10.0))
                .andExpect(jsonPath("$.batchStock.[0].minimumTemperature").value(5.0))
                .andExpect(jsonPath("$.batchStock.[1].minimumTemperature").value(5.0))
                .andExpect(jsonPath("$.batchStock.[1].minimumTemperature").value(5.0));

    }

}
