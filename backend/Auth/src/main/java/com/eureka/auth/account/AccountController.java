package com.eureka.auth.account;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"1. Account"})
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

    @ApiOperation(value="회원가입", notes = "회원가입 정보 입력받고, 저장한 값 반환", httpMethod = "POST")
    @PostMapping("/add")
    public Account createAccount (@ApiParam(value="사용자 입력 데이터") @RequestBody Account account){
        System.out.println("login/add 들어왔다");
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        System.out.println("/login/add "+account.toString());
        return accountRepository.save(account);
    }


    /*
    @PostMapping("/addAdmin")
    public void createAdmin(@ApiParam(value="사용자 입력 데이터") @RequestBody Account account){
        System.out.println("createadmin in!");
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        Account result = accountRepository.save(account);
        System.out.println(result.toString());
    }
     */

    @ApiOperation(value="회원정보 수정x", notes = "회원 정보 입력받고, 수정한 값 반환", httpMethod = "PUT")
    @PutMapping("/update")
    public Account updateAccount(@ApiParam(value="사용자 입력 데이터") @RequestBody Account account) {
        Account existingAccount = accountRepository.findById(account.getUsername()).orElse(null);
        existingAccount.setPassword(account.getPassword());
        return accountRepository.save(existingAccount);
    }

}
