package com.digital.bank.account.stepdef;


import com.digital.bank.account.domain.CreateAccountResponse;
import com.digital.bank.account.domain.UserAccountVO;
import com.digital.bank.account.pojo.UserAccountTestDto;
import com.digital.bank.account.domain.port.UserAccountService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


@ContextConfiguration
@SpringBootTest
@ActiveProfiles("test")
public class OpenAccountStepDef {

    @Autowired
    UserAccountService userAccountService;
    private List<CreateAccountResponse> actualCreatedUserAccountList = new ArrayList<>();
    private int count = 0;



    @Given("^Jonn has provided the information as$")
    public void jonn_has_provided_the_information_as(List<UserAccountTestDto> userAccountList) throws Throwable {

        userAccountList.forEach(userAccountTest -> {

            UserAccountVO userAccountVO = new UserAccountVO();
            BeanUtils.copyProperties(userAccountTest, userAccountVO);
            actualCreatedUserAccountList.add(userAccountService.createUserAccount(userAccountVO));
        });

    }

    @Then("^Account should be created and he should get a message as$")
    public void john_account_should_be_created_and_he_should_get_a_message_as(List<CreateAccountResponse> testAccountCreateResponseList) throws Throwable {

        actualCreatedUserAccountList.forEach(createAccountResponseDto -> {

            assertThat(createAccountResponseDto.getMessage(), is(testAccountCreateResponseList.get(count).getMessage()));
            count++;

        });
    }


    @Then("^Account will be created as|$")
    public void john_s_account_will_be_created_as(List<UserAccountTestDto> testAccountList) throws Throwable {

        actualCreatedUserAccountList.forEach(createAccountResponseDto -> {

            UserAccountVO userAccountVOActual = createAccountResponseDto.getUserAccountVO();
            UserAccountTestDto userAccountDtoExpected = testAccountList.get(count);

            assertThat(userAccountVOActual.getAccountNumber(), is(userAccountDtoExpected.getAccountNumber()));
            assertThat(userAccountVOActual.getAddress(), is(userAccountDtoExpected.getAddress()));
            assertThat(userAccountVOActual.getCurrency(), is(userAccountDtoExpected.getCurrency()));
            assertThat(userAccountVOActual.getFirstName(), is(userAccountDtoExpected.getFirstName()));
            assertThat(userAccountVOActual.getLastName(), is(userAccountDtoExpected.getLastName()));
            assertThat(userAccountVOActual.getMiddleName(), is(userAccountDtoExpected.getMiddleName()));
            assertThat(userAccountVOActual.getKycDocument(), is(userAccountDtoExpected.getKycDocument()));
            assertThat(userAccountVOActual.getKycIdentificationNo(), is(userAccountDtoExpected.getKycIdentificationNo()));
            assertThat(userAccountVOActual.getAmount() , is(userAccountDtoExpected.getAmount()));

            userAccountVOActual = null;
            userAccountDtoExpected = null;

            count++;

        });


    }


    @Then("^John should get message as|$")
    public void john_should_get_message_as(List<CreateAccountResponse> testAccountCreateResponseList) throws Throwable {

        actualCreatedUserAccountList.forEach(createAccountResponseDto -> {
            assertThat(createAccountResponseDto.getMessage(), is(testAccountCreateResponseList.get(count).getMessage()));
            count++;

        });


    }
}
