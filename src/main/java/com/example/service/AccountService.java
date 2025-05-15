package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public ResponseEntity<Account> register(Account account){
        if(account.getUsername() == null || account.getPassword() == null || account.getPassword().length() <4){
            return ResponseEntity.badRequest().build();
        }
        if(accountRepository.findByUsername(account.getUsername()).isPresent()){
            return ResponseEntity.status(409).build();
        }
        Account saved = accountRepository.save(account);
        return ResponseEntity.ok(saved);
    }

    public ResponseEntity<Account> login(Account account){
        return accountRepository.findByUsername(account.getUsername()).filter(a -> a.getPassword().equals(account.getPassword())).map(ResponseEntity::ok).orElse(ResponseEntity.status(401).build());
    }
}
