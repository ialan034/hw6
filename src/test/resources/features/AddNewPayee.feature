@add_new_payee
Feature: Add new payee under pay bills

  Background:User navigates to the Pay Bills page
    Given user is on the main page
    Then user navigates to the login page
    Then user logs in with default credentials
    Then user navigates to the "Pay Bills" page
    And user navigates to the "Add New Payee" tab

  Scenario: Add a new payee
    And user creates new payee using following information
      | Payee Name    | The Law Offices of Hyde, Price & Scharks |
      | Payee Address | 100 Same st, Anytown, USA, 10001         |
      | Account       | Checking                                 |
      | Payee details | XYZ account                              |
    Then verifies successful submission message "The new payee The Law Offices of Hyde, Price & Scharks was successfully created."


