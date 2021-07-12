package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.unit;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.AccountRepository;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.session.SessionServiceImpl;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.util.MockitoExtension;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class SessionServiceTest {
    @Mock
    private  AccountRepository accountRepository;

    @InjectMocks
    private SessionServiceImpl service;

}
