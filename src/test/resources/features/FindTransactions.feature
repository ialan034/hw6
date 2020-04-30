@find_transactions
Feature: Find transactions in Account Activity

  Background: Navigate to Find Transactions in Account Activity page
    Given user is on the main page
    Then user navigates to the login page
    Then user logs in with default credentials
    Then user navigates to the "Account Activity" page
    And user navigates to the "Find Transactions" tab

  Scenario: Search date range
    When the user enters date range from "2012-09-01" to "2012-09-06"
    And clicks search
    Then results table should only show transactions dates between "2012-09-01" to "2012-09-06"
    And the results should be sorted by most recent date
      | 2012-09-06 |
      | 2012-09-05 |
      | 2012-09-01 |
    When the user enters date range from "2012-09-02" to "2012-09-06"
    And clicks search
    Then results table should only show transactions dates between "2012-09-02" to "2012-09-06"
    And the results table should only not contain transactions dated "2012-09-01"

  Scenario: Search description
    When the user enters description "ONLINE"
    And clicks search
    Then results table should only show descriptions containing "ONLINE"
    When the user enters description "OFFICE"
    And clicks search
    Then results table should only show descriptions containing "OFFICE"
    But results table should not show descriptions containing "ONLINE"

#    work on it , there should be better solution
  Scenario: Search description case insensitive
    When the user enters description "ONLINE"
    And clicks search
    Then results table should only show descriptions containing "ONLINE"
    When the user enters description "online"
    And clicks search
    Then results table should show same descriptions with "ONLINE" search

  Scenario: Type
    When clicks search
    Then results table should show at least one result under Deposit
    Then results table should show at least one result under Withdrawal
    When user selects type "Deposit"
    Then results table should show at least one result under Deposit
    But results table should show no result under "Withdrawal"
    When user selects type "Withdrawal"
    Then results table should show at least one result under Withdrawal
    But results table should show no result under "Deposit"