package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.integration;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.InboundOrderRepository;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.util.TestUtilsGenerator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext( classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderTest  extends IntegrationTest{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InboundOrderRepository inboundOrderRepository;

    String token;

    @BeforeAll
    void setup(){
        token = TestUtilsGenerator.getClientToken();
    }

    @Test
    void testCreateNewOrder() throws Exception {
        inboundOrderRepository.save(TestUtilsGenerator.createTwoBatchesInboundOrderToPersistByWarehouseCodeWithOneExpiringBatch("CAJF"));
        inboundOrderRepository.save(TestUtilsGenerator.createTwoBatchesInboundOrderToPersistByWarehouseCodeWithOneExpiringBatch("OSAF"));

        String request = TestUtilsGenerator.createOrderRequest();
        
        this.mockMvc.perform(
            post("/api/v1/fresh-products/orders/")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token)
                .content(request))   
            .andDo(print()).andExpect(status().isCreated());
    }
}
