@account_summary
Feature: Account Summary
  As user I want to summary of my accounts in this page

  Background: Navigate to Account Summary Page
    Given user is on the main page
    Then user navigates to the login page
    Then user logs in with default credentials

  Scenario: User verifies page title
    Then user verifies that page title is "Zero - Account Summary"

  Scenario: User verifies that Account Summary page has following account types
    Then Verifies account types in the page
      | Cash Accounts       |
      | Investment Accounts |
      | Credit Accounts     |
      | Loan Accounts       |

  Scenario: User verifies that Credit Accounts Table has following headers
    Then Verifies Credit Accounts Table headers
      | Account     |
      | Credit Card |
      | Balance     |
