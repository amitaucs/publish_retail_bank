package com.digital.bank.account.domain.port;

import com.digital.bank.account.domain.CreateAccountResponse;
import com.digital.bank.account.domain.UserAccountVO;

import java.util.List;
import java.util.Optional;

public interface UserAccountService {

     CreateAccountResponse createUserAccount(UserAccountVO userAccountVO);
     Optional<List<CreateAccountResponse>> fetchAllUserAccount();

}
