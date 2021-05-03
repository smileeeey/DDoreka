package com.eureka.auth.account;

import org.checkerframework.checker.units.qual.A;
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
        System.out.println("Account 생성자 호출");
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/add")
    public Account createAccount (@RequestBody Account account){
        System.out.println("login/add 들어왔다");
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        System.out.println("/login/add "+account.toString());
        return accountRepository.save(account);
    }

    @PostMapping("/addAdmin")
    public void createAdmin(@RequestBody Account account){
        System.out.println("createadmin in!");
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        Account result = accountRepository.save(account);
        System.out.println(result.toString());
    }

    @PutMapping("/update")
    public Account updateAccount(@RequestBody Account account) {
        Account existingAccount = accountRepository.findById(account.getUsername()).orElse(null);
        existingAccount.setPassword(account.getPassword());
        return accountRepository.save(existingAccount);
    }

}
