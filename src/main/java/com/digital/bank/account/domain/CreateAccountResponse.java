package com.digital.bank.account.domain;

public class CreateAccountResponse {

    private String message;
    private UserAccountVO userAccountVO;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserAccountVO getUserAccountVO() {
        return userAccountVO;
    }

    public void setUserAccountVO(UserAccountVO userAccountVO) {
        this.userAccountVO = userAccountVO;
    }
}
