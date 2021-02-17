package com.eureka.auth.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class AccountController {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountController(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/add")
    public Account createAccount (@RequestBody Account account){
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    @PutMapping("/update")
    public Account updateAccount(@RequestBody Account account) {
        Account existingAccount = accountRepository.findById(account.getUsername()).orElse(null);
        existingAccount.setPassword(account.getPassword());
        return accountRepository.save(existingAccount);
    }

}
