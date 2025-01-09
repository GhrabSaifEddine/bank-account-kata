# Read Me First

This Kata serves as a mini project for managing bank account that supports functionalities :
 * Deposit & withdrawal amounts
 * Get actual account statement and list all account transactions

Technically, I choose to create a micro-services architecture, based on spring boot applications.

2 micro-services ( Command & Query) have been created in order to respect CQRS pattern.

Hexagonal Pattern also was used in order to separate internal logical functionalities by exposing classes in application, domain and infrastructure layers.

The database used is H2. I choose it for its lightweight use, easy configuration and simple use ( H2 console is setted activated in conf files).
Entity-relationship diagram is composed of 2 tables "bank_account" & "transaction", that widely responds to our need.

Tests written for this project : Unitary tests, Integration tests & BDD tests using Cucumber.


<b>Looking forward to discuss further the technical details.</b>

# Useful curl calls

( In all examplex below, "1" is the bank account identifier used for calls )

Deposit amount : 
* curl --location 'localhost:8080/api/accounts/1/deposit' \
  --header 'Content-Type: application/json' \
  --data '{
  "amount": 100.36
  }'

Withdrawal amount : 
* curl --location 'localhost:8080/api/accounts/1/withdrawal' \
  --header 'Content-Type: application/json' \
  --data '{
  "amount": 6.98
  }'

Get actual account balance
* curl --location 'localhost:8081/api/accounts/1'

List account statements
* curl --location 'localhost:8081/api/accounts/1/transactions'
