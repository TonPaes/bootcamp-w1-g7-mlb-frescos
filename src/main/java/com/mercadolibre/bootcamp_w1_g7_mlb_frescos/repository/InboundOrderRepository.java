package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.InboundOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InboundOrderRepository extends JpaRepository<InboundOrder, Integer> {
}
