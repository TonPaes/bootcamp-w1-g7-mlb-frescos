package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.controller;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.BatchStockDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.InboundOrderDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.inboundorder.InboundOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("inboundorder")
public class InboundOrderController {

    private InboundOrderService inboundOrderService;

    public InboundOrderController(InboundOrderService inboundOrderService) {
        this.inboundOrderService = inboundOrderService;
    }

    @PostMapping()
    public ResponseEntity<BatchStockDTO> createInboundOrder(@RequestBody InboundOrderDTO inboundOrderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(inboundOrderService.createInboundOrder(inboundOrderDTO));
    }
}
