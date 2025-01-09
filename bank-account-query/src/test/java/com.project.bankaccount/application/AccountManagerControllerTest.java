package com.project.bankaccount.application;

import com.project.bankaccount.QueryApplication;
import com.project.bankaccount.domain.dto.BankAccountDto;
import com.project.bankaccount.domain.dto.TransactionDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpMethod.GET;

@SpringBootTest(classes = QueryApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountManagerControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Sql(scripts={"classpath:init_script.sql"})
    @Test
    public void shouldGetAccountStatementAndPrintAccountStatementsWithSuccess() {
        // When
        ResponseEntity<BankAccountDto> responseEntity = this.restTemplate
            .getForEntity("http://localhost:" + port + "/api/accounts/" + 1, BankAccountDto.class);
        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("1", responseEntity.getBody().getAccountId());
        assertEquals("ADMIN", responseEntity.getBody().getClientName());
        assertEquals(BigDecimal.ZERO.signum(), responseEntity.getBody().getBalance().signum());

        // When
        ParameterizedTypeReference<List<TransactionDto>> responseType = new ParameterizedTypeReference<List<TransactionDto>>() {};
        ResponseEntity<List<TransactionDto>> response = restTemplate.exchange("http://localhost:" + port + "/api/accounts/" + 1 + "/transactions",
            GET, null, responseType);

        // Then

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, response.getBody().size());
        assertEquals("0e8328c1-0127-49fa-8b83-a0d4e14ebecd", response.getBody().get(0).getTransactionId());
        assertEquals("0e8328c1-0127-49fa-8b83-a0d4e14ebecf", response.getBody().get(1).getTransactionId());
        assertEquals("0e8328c1-0127-49fa-8b83-a0d4e14ebecg", response.getBody().get(2).getTransactionId());
    }

    @Test
    public void shouldFailWhenGetAccountStatementToInexistantBankAccount() {
        // When
        ResponseEntity<BankAccountDto> responseEntity = this.restTemplate
            .getForEntity("http://localhost:" + port + "/api/accounts/" + 9865, BankAccountDto.class);
        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
}
