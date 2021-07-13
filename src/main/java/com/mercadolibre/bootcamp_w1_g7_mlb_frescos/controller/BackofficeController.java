package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.controller;

import java.util.List;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.ClientDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.account.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backoffice")
public class BackofficeController {
    
    @Autowired
    private AccountService accountService;

    @GetMapping("/list_accounts")
    public ResponseEntity<List<ClientDTO>> listAccounts(){
        return ResponseEntity.status(200).body(accountService.listUsers());
    }
}
