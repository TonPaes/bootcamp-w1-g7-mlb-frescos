package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository;

import java.util.List;
import java.util.UUID;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    @Query("FROM Account a WHERE a.userName = :userName")
    Account findAccount(@Param("userName") String userName);

    @Query(value = "SELECT c FROM Account c WHERE c.role.id = :role")
    List<Account> findAllClients(@Param("role") String role);
}