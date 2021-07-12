package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.account;

import java.util.List;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.ClientDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Account;

import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    Account getAccountByName(String userName);
    List<ClientDTO> listUsers();
}
