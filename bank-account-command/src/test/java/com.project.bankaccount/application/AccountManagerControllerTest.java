package com.project.bankaccount.application;

import com.project.bankaccount.CommandApplication;
import com.project.bankaccount.domain.dto.AmountDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = CommandApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountManagerControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldMakeDepositWithSuccess() {
        AmountDto amountDto = new AmountDto(BigDecimal.TEN);
        ResponseEntity<String> responseEntity = this.restTemplate
            .postForEntity("http://localhost:" + port + "/api/accounts/" + 1 + "/deposit", amountDto,
                String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Deposit amount has been processed successfully! ", responseEntity.getBody());
    }

    @Test
    public void shouldFailWhenDepositDueToInexistantBankAccount() {
        AmountDto amountDto = new AmountDto(BigDecimal.TEN);
        ResponseEntity<String> responseEntity = this.restTemplate
            .postForEntity("http://localhost:" + port + "/api/accounts/" + 15 + "/deposit", amountDto,
                String.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Bank Account identifier is not valid!", responseEntity.getBody());
    }

    @Test
    public void shouldMakeWithDrawalWithSuccess() {
        AmountDto amountDto = new AmountDto(BigDecimal.TEN);
        ResponseEntity<String> responseEntity = this.restTemplate
            .postForEntity("http://localhost:" + port + "/api/accounts/" + 1 + "/withdrawal", amountDto,
                String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Withdrawal amount has been processed successfully! ", responseEntity.getBody());
    }

    @Test
    public void shouldFailWhenWithdrawalDueToInexistantBankAccount() {
        AmountDto amountDto = new AmountDto(BigDecimal.TEN);
        ResponseEntity<String> responseEntity = this.restTemplate
            .postForEntity("http://localhost:" + port + "/api/accounts/" + 15 + "/withdrawal", amountDto,
                String.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Bank Account identifier is not valid!", responseEntity.getBody());
    }

    @Test
    public void shouldFailWhenWithdrawalDueToInsufficientBankAccountBalance() {
        AmountDto amountDto = new AmountDto(BigDecimal.TEN);
        ResponseEntity<String> responseEntity = this.restTemplate
            .postForEntity("http://localhost:" + port + "/api/accounts/" + 1 + "/withdrawal", amountDto,
                String.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Withdrawal exceeds account amount!", responseEntity.getBody());
    }
}
