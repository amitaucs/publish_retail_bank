package com.digital.bank.account.domain;

import com.digital.bank.account.domain.port.SavingAccountDao;
import com.digital.bank.account.domain.port.UserAccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserAccountServiceImpl implements UserAccountService {


    @Autowired
    SavingAccountDao savingAccountDao;

    @Autowired
    UserAccountValidator userAccountValidator;

    @Value(value = "${user_bank.message.success}")
    String messageSuccess;

    @Value(value = "${user_bank.message.fail}")
    String messageFail;

    @Value(value = "${user_bank.message.hello}")
    String hello;

    @Value(value = "${user_bank.message.str}")
    String comma;

    @Value(value = "${user_bank.message.emptySpace}")
    String emptySpace;

    @Override
    public CreateAccountResponse createUserAccount(UserAccountVO userAccountVO) {

        CreateAccountResponse createAccountResponse = new CreateAccountResponse();
        userAccountValidator.validateUserAccount(userAccountVO, createAccountResponse);

        if (StringUtils.isEmpty(createAccountResponse.getMessage())) {

            persistUserAccount(userAccountVO, createAccountResponse);
        }

        return createAccountResponse;
    }

    private void persistUserAccount(UserAccountVO userAccountVO, CreateAccountResponse createAccountResponse) {
        SavingAccount savingAccount = new SavingAccount();
        BeanUtils.copyProperties(userAccountVO, savingAccount);
        savingAccount.setAccountNumber(userAccountVO.getFirstName() + userAccountVO.getKycIdentificationNo());
        savingAccountDao.save(savingAccount);

        Optional<SavingAccount> optionalSavingAccountSaved = savingAccountDao.findByAccountNumber(savingAccount.getAccountNumber());
        if (optionalSavingAccountSaved.isPresent()) {


            UserAccountVO userAccountVOPostSave = new UserAccountVO();
            savingAccount = optionalSavingAccountSaved.get();

            String message = buildMessage(savingAccount);

            createAccountResponse.setMessage(message);
            BeanUtils.copyProperties(savingAccount, userAccountVOPostSave);
            createAccountResponse.setUserAccountVO(userAccountVOPostSave);

        } else {

            String message = new StringBuilder(hello).append(savingAccount.getFirstName())
                    .append(comma).append(messageFail).toString();
            createAccountResponse.setMessage(message);

        }
    }

    private String buildMessage(SavingAccount savingAccount) {
        return new StringBuilder(hello).append(emptySpace).append(savingAccount.getFirstName()).append(comma)
                .append(emptySpace).append(messageSuccess).append(emptySpace)
                .append(savingAccount.getAccountNumber()).toString();
    }

    @Override
    public Optional<List<CreateAccountResponse>> fetchAllUserAccount() {

        List<SavingAccount> userAccountList = savingAccountDao.findAll();

        List<CreateAccountResponse> createAccountResponseList = userAccountList.stream().map(userAccount -> {

            UserAccountVO userAccountVO = new UserAccountVO();
            BeanUtils.copyProperties(userAccount, userAccountVO);
            CreateAccountResponse createAccountResponse = new CreateAccountResponse();
            createAccountResponse.setUserAccountVO(userAccountVO);
            return createAccountResponse;
        }).collect(Collectors.toList());

        return Optional.of(createAccountResponseList);

    }
}
