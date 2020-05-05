package com.digital.bank.account.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserAccountValidator {

    @Value(value = "${user_bank.message.lessAmount}")
    String messageFail;

    @Value(value = "${user_bank.message.hello}")
    String hello;

    @Value(value = "${user_bank.message.str}")
    String comma;

    @Value(value = "${user_bank.message.emptySpace}")
    String emptySpace;

    @Value(value = "${user_bank.minumumBalance}")
    long minBalance;



    public CreateAccountResponse validateUserAccount (UserAccountVO userAccountVO, CreateAccountResponse createAccountResponse){

        if(userAccountVO.getAmount() < minBalance){

            createAccountResponse.setMessage(new StringBuilder(hello).append(emptySpace)
                    .append(userAccountVO.getFirstName()).append(comma).append(emptySpace).append(messageFail).toString());
        }

        return createAccountResponse;

    }

}
