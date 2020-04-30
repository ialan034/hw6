@login
Feature: Login functionality
  As user, I want to be able to login with valid user name and password

  Background: Navigate to login page
    Given user is on the main page
    Then user navigates to the login page

  Scenario: Authorized user should be able to login to the application
    When user logs in with "username" and "password"
    Then verifies that "Account Summary" page is displayed

  Scenario: Unauthorized user should not be able to login
    When user logs in with "invalid" and "invalid"
    Then verifies that warning message is displayed