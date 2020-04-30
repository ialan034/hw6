Feature: Pay bills
  User should be able to pay bills successfully

  Background:User navigates to the Pay Bills page
    Given user is on the main page
    Then user navigates to the login page
    Then user logs in with default credentials
    Then user navigates to the "Pay Bills" page


  Scenario: User verifies page title
    Then user verifies that page title is "Zero - Pay Bills"

  @pay_bills
  Scenario Outline: Submit a successful payment
    Then user selects payee: "<Payee>"
    And user selects account type: "<Account Type>"
    And user enters payment amount: "<Payment Amount>"
    And user enters payment date: "<Payment Date>"
    And user enters payment description: "<Payment Description>"
    Then user submit payment
    And verifies successful submission message "The payment was successfully submitted."
    Examples:
      | Payee  | Account Type | Payment Amount | Payment Date | Payment Description |
      | Sprint | Savings      | 100            | 2020-04-29   | Cell phone payment  |

  Scenario: Try to submit a payment without entering the amount
    Then user selects payee: "Sprint"
    And user selects account type: "Savings"
    Then user submit payment
    Then Verify you will get "Please fill out this field." message on amount

  Scenario: Try to submit a payment without entering the date
    Then user selects payee: "Sprint"
    And user selects account type: "Savings"
    And user enters payment amount: "100"
    Then user submit payment
    Then Verify you will get "Please fill out this field." message on date field

  @pay_bills
  Scenario Outline: Amount field should not accept alphabetical or special characters
    Then user selects payee: "<Payee>"
    And user selects account type: "<Account Type>"
    And user enters payment amount: "<Payment Amount>"
    And user enters payment date: "<Payment Date>"
    And user enters payment description: "<Payment Description>"
    Then user submit payment
    And verifies successful submission message "The payment was successfully submitted." is not displayed
    Examples:
      | Payee  | Account Type | Payment Amount | Payment Date | Payment Description |
      | Sprint | Savings      | A23-*          | 2020-04-29   | Cell phone payment  |

  Scenario Outline: Date field should not accept alphabetical characters
    Then user selects payee: "<Payee>"
    And user selects account type: "<Account Type>"
    And user enters payment amount: "<Payment Amount>"
    And user enters payment date: "<Payment_Date>"
    And user enters payment description: "<Payment Description>"
    Then user submit payment
    And Verify you will get "Please fill out this field." message on date field
    Examples:
      | Payee  | Account Type | Payment Amount | Payment_Date | Payment Description |
      | Sprint | Savings      | 200            | January      | Cell phone payment  |