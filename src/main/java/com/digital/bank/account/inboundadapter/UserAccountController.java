package com.digital.bank.account.inboundadapter;


import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@EnableDiscoveryClient
@CrossOrigin(value = "*")
public class UserAccountController {

    @GetMapping(path = "/getCustomerDetails")
    public ResponseEntity<List<String>> createUserAccount() {
        return ResponseEntity.ok().body(Arrays.asList("John", "Asit", "Subha"));
    }

}
