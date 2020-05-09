package com.digital.bank.account.inboundadapter;


import com.digital.bank.account.domain.CreateAccountResponse;
import com.digital.bank.account.domain.UserAccountVO;
import com.digital.bank.account.domain.port.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@EnableDiscoveryClient
@CrossOrigin(value = "*")
@RequestMapping("/account")
public class UserAccountController {

    @Autowired
    UserAccountService userAccountService;


    @PostMapping(path = "/createUserAccount")
    public ResponseEntity<CreateAccountResponse> createUserAccount(@RequestBody UserAccountVO userAccountVO){

        CreateAccountResponse createAccountResponse = userAccountService.createUserAccount(userAccountVO);
        if(null != createAccountResponse.getUserAccountVO()){
            return ResponseEntity.ok().body(createAccountResponse);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createAccountResponse);
        }

    }


    @GetMapping("/findAllAccount")
    public ResponseEntity<List<CreateAccountResponse>> getAllUserAccount(){

        Optional<List<CreateAccountResponse>> createAccountResponseDtoList = userAccountService.fetchAllUserAccount();
        if(createAccountResponseDtoList.isPresent()){
            return ResponseEntity.ok().body(createAccountResponseDtoList.get());
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }
}
