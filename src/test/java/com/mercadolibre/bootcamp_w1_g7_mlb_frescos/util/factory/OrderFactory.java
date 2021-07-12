package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.util.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Order;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Product;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.ProductOrder;

public class OrderFactory {
    static public Order getOrderThatExits(){
        List<ProductOrder> detailOrder =  new ArrayList<>();
        Product product0 = new Product();
        product0.setId(UUID.fromString("51b3b287-0b78-484c-90c3-606c4bae9401"));
        product0.setName("Alface");
        product0.setCategory("RS");
        product0.setPrice(10D);

        ProductOrder productOrder0 = new ProductOrder();
        productOrder0.setProduct(product0);
        productOrder0.setQuantity(10);
        
        detailOrder.add(productOrder0);
        
        return new Order(
            UUID.fromString("1b0d82fa-277f-4f13-a9b7-a6c4c4eec204"),
            300D,
            detailOrder
        );
    }
}
