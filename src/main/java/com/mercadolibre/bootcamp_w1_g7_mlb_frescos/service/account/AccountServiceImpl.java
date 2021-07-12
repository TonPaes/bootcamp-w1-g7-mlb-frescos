package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.account;

import java.util.ArrayList;
import java.util.List;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.ClientDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Account;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account getAccountByName(String userName) {

        return accountRepository.findAccount(userName);
    }

    @Override
    public List<ClientDTO> listUsers() {
        List<ClientDTO> clientList = new ArrayList<ClientDTO>();
        for (Account account : accountRepository.findAllClients("ROLE_CLIENT")) {
            clientList.add(new ClientDTO(account.getUserName(), account.getId(), account.getRole().getId()));
        }
        return clientList;
    }
}
