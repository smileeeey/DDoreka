package com.eureka.auth.account;

import com.netflix.ribbon.proxy.annotation.Http;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = {"1. Account"})
@RestController
@RequestMapping("/login")
@CrossOrigin
@AllArgsConstructor
public class AccountController {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountService accountService;

    @ApiOperation(value="회원가입", notes = "회원가입 정보 입력받고, 저장한 값 반환", httpMethod = "POST")
    @PostMapping("/add")
    public Account createAccount (@ApiParam(value="사용자 입력 데이터") @RequestBody Account account){
        System.out.println("login/add 들어왔다");
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        System.out.println("/login/add "+account.toString());
        return accountRepository.save(account);
    }

    @ApiOperation(value="이메일 중복체크", notes = "이미 가입된 이메일인지 체크하고 반환", httpMethod = "POST")
    @PostMapping("/email-check")
    public ResponseEntity<?> createAccount (@ApiParam(value="이메일 주소") @RequestBody Map<String,String> param){
        Map<String,Object> result = new HashMap<>();
        try {
            System.out.println(param.get("email"));
            result.put("isAvail",accountService.checkEmail(param.get("email")));
//            result.put("isAvail",accountService.checkEmail("goo@goo.com"));

            return new ResponseEntity(result,null, HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(result,null,HttpStatus.BAD_REQUEST); //이거맞나?
        }
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
