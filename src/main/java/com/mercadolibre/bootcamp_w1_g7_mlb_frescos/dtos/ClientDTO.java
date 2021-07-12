package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private String userName;
    private UUID id;
    private String role;
}
