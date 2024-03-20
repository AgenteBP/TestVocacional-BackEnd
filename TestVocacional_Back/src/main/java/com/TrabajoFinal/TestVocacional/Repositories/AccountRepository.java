package com.TrabajoFinal.TestVocacional.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TrabajoFinal.TestVocacional.Models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{

    Optional<Account> findByUserName(String userName);
    
}
