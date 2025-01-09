package com.project.bankaccount;

import com.project.bankaccount.domain.dto.BankAccountDto;
import com.project.bankaccount.domain.dto.TransactionDto;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpMethod.GET;

@CucumberContextConfiguration
@SpringBootTest(classes = QueryApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class SpringIntegrationTest {
    private static final String BASIC_URL = "http://localhost:8081/api/accounts/";
    protected RestTemplate restTemplate = null;

    ResponseEntity<BankAccountDto> getAccountBalance(String clientId) {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");

        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }

        ResponseEntity<BankAccountDto> operationsResponse =
            restTemplate.getForEntity(BASIC_URL + clientId, BankAccountDto.class);
        return operationsResponse;
    }

    ResponseEntity<List<TransactionDto>> loadAccountStatements(String clientId) {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");

        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }

        ParameterizedTypeReference<List<TransactionDto>> responseType =
            new ParameterizedTypeReference<>() {
            };
        ResponseEntity<List<TransactionDto>> operationsResponse =
            restTemplate.exchange(BASIC_URL + clientId + "/transactions", GET, null, responseType);

        return operationsResponse;
    }
}