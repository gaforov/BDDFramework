@login
Feature: Login

  @smoke
  Scenario: valid admin login
#    Given user is navigated to HRM homepage  <-- Hooks @Before takes care of this.
    And user enters valid admin username and password
    When user clicks on login button
    Then admin user is successfully logged in

  @smoke
  Scenario: valid ess login
    And user enters valid ess username and password
    When user clicks on login button
    Then ess user is successfully logged in

  @smoke @regression
  Scenario: login with valid username and invalid password
    And user enters valid username and invalid password
    When user clicks on login button
    Then user see invalid credentials text on login page

