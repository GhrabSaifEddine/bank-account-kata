package com.project.bankaccount;

import com.project.bankaccount.domain.dto.AmountDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static com.project.bankaccount.domain.dto.TransactionType.DEPOSIT;
import static com.project.bankaccount.domain.dto.TransactionType.WITHDRAWAL;
import static org.junit.Assert.assertEquals;


public class CucumberStepDefinitions extends SpringIntegrationTest {

    private String clientID = null;
    private ResponseEntity<String> response;

    @Given("^the client id (.+)$")
    public void the_client_issues_GET_hello(String clientId) throws Throwable {
        this.clientID = clientId;
    }

    @When("^the client make deposit of (.+)$")
    public void the_client_make_deposit(BigDecimal amount) throws Throwable {
        response = makeTransaction(this.clientID, DEPOSIT, new AmountDto(amount));
    }

    @When("^the client make withdrawal of (.+)$")
    public void the_client_make_withdrawal(BigDecimal amount) throws Throwable {
        response = makeTransaction(this.clientID, WITHDRAWAL, new AmountDto(amount));
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) {
        assertEquals(statusCode, response.getStatusCode().value());
    }

    @Then("^the shown message is (.+)$")
    public void the_shown_message_is(String responseMsg) {
        assertEquals(responseMsg, response.getBody());
    }
}