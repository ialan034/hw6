Feature: Navigating to specific accounts in Accounts Activity

  Background: Navigate to Account Summary Page
    Given user is on the main page
    Then user navigates to the login page
    Then user logs in with default credentials

  @account_navigation
  Scenario Outline: <Account_Type> account redirect
    When the user clicks "<Account_Type>" link on the Account Summary Page
    Then verifies that "Account Activity" page is displayed
    And Verify that default option for account type is "<Account_Type>"
    Examples:
      | Account_Type |
      | Savings      |
      | Brokerage    |
      | Checking     |
      | Credit Card  |
      | Loan         |
