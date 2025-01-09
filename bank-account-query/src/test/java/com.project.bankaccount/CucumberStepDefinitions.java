package com.project.bankaccount;

import com.project.bankaccount.domain.dto.BankAccountDto;
import com.project.bankaccount.domain.dto.TransactionDto;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CucumberStepDefinitions extends SpringIntegrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String clientID = null;
    private ResponseEntity<BankAccountDto> accountStatementResponse;
    private ResponseEntity<List<TransactionDto>> allAccountStatementsResponse;

    @Given("^the client id (.+)$")
    public void the_client_issues_GET_hello(String clientId) throws Throwable {
        this.clientID = clientId;
        ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(), new ClassPathResource("init_script.sql"));
    }

    @When("^the client gets his account statement$")
    public void the_client_get_account_statement() throws Throwable {
        accountStatementResponse = getAccountBalance(this.clientID);
    }

    @When("^the client loads all his account statements$")
    public void the_client_loads_all_his_account_statements() throws Throwable {
        allAccountStatementsResponse = loadAccountStatements(this.clientID);
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) {
        assertEquals(statusCode, accountStatementResponse.getStatusCode().value());
    }

    @Then("^the client receives status code of account statements equal to (\\d+)$")
    public void the_client_receives_status_code_account_statements_of(int statusCode) {
        assertEquals(statusCode, allAccountStatementsResponse.getStatusCode().value());
    }

    @Then("^the loaded account is not null$")
    public void the_loaded_account_is_not_null() {
        assertNotNull(accountStatementResponse.getBody());
    }

    @And("^the size of account statements is (\\d+)$")
    public void the_size_of_account_statements_is(int size) {
        assertEquals(size, allAccountStatementsResponse.getBody().size());
    }

}