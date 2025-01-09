Feature: Fetching account operations

  Scenario: client consult his account
    Given the client id 1
    When the client gets his account statement
    Then the client receives status code of 200
    And the loaded account is not null
    When the client loads all his account statements
    Then the client receives status code of account statements equal to 200
    And the size of account statements is 3
