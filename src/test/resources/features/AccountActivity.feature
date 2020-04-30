@account_activity
Feature: Account activity page
  Account activity page should display account related transactions

  Background: Navigate to Account Activity page
    Given user is on the main page
    Then user navigates to the login page
    Then user logs in with default credentials
    Then user navigates to the "Account Activity" page

  Scenario: User verifies page title
    Then user verifies that page title is "Zero - Account Activity"

  Scenario: User verifies default option for account
    Then Verify that default option for account type is "Savings"


  Scenario: User verifies account drop down has following options
    Then Verify that dropdown has following options
      | Savings      |
      | Checking     |
      | Loan         |
      | Credit  Card |
      | Brokerage    |

  Scenario: Verify Transactions table headers
    Then Verify that Transactions table has following headers
      | Date        |
      | Description |
      | Deposit     |
      | Withdrawal  |
