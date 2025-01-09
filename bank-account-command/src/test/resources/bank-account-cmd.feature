Feature: Processing account operations

  Scenario: client makes deposit operation over his account ( US1 )
    Given the client id 1
    When the client make deposit of 100
    Then the client receives status code of 200
    And the shown message is Deposit amount has been processed successfully!

  Scenario: client makes withdrawal operation over his account ( US2 )
    Given the client id 1
    When the client make withdrawal of 17.98
    Then the client receives status code of 200
    And the shown message is Withdrawal amount has been processed successfully!
