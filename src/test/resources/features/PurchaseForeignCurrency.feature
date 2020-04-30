Feature: Purchase Foreign Currency

  Background:User navigates to the Pay Bills page
    Given user is on the main page
    Then user navigates to the login page
    Then user logs in with default credentials
    Then user navigates to the "Pay Bills" page
    And user navigates to the "Purchase Foreign Currency" tab

  Scenario: Available currencies
    Then following currencies should be available
      | Australia (dollar)    |
      | Canada (dollar)       |
      | Switzerland (franc)   |
      | China (yuan)          |
      | Denmark (krone)       |
      | Eurozone (euro)       |
      | Great Britain (pound) |
      | Hong Kong (dollar)    |
      | Japan (yen)           |
      | Mexico (peso)         |
      | Norway (krone)        |
      | New Zealand (dollar)  |
      | Sweden (krona)        |
      | Singapore (dollar)    |
      | Thailand (baht)       |

  Scenario: Error message for not selecting currency
    Then user enters amount "100"
    When user tries to calculate cost without selecting a currency
    Then error message should be displayed

  @purchase_foreign_currency
  Scenario: Error message for not entering value
    Then user selects a currency "Canada (dollar)"
    When user tries to calculate cost without entering a value
    Then error message should be displayed