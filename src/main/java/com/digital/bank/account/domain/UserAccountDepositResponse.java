package com.digital.bank.account.domain;

public class UserAccountDepositResponse {

    String message;
    UserAccountDepositRequest userAccountDepositRequest;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserAccountDepositRequest getUserAccountDepositRequest() {
        return userAccountDepositRequest;
    }

    public void setUserAccountDepositRequest(UserAccountDepositRequest userAccountDepositRequest) {
        this.userAccountDepositRequest = userAccountDepositRequest;
    }
}
