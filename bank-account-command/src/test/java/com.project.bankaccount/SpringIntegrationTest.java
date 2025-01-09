package com.project.bankaccount;

import com.project.bankaccount.domain.dto.AmountDto;
import com.project.bankaccount.domain.dto.TransactionType;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@CucumberContextConfiguration
@SpringBootTest(classes = CommandApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class SpringIntegrationTest {
    private static final String BASIC_URL = "http://localhost:8080" + "/api/accounts/";
    protected RestTemplate restTemplate = null;

    ResponseEntity<String> makeTransaction(String clientId, TransactionType type, AmountDto amountDto) {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");

        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }

        ResponseEntity<String> operationsResponse = restTemplate
            .postForEntity(BASIC_URL + clientId + "/" + type.name().toLowerCase(),
                amountDto,
                String.class);
        return operationsResponse;
    }
}