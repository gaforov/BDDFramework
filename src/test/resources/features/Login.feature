@login
Feature: Login

  @smoke
  Scenario: valid admin login
    And user enters valid admin username and password
    When user clicks on login button
    Then admin user is successfully logged in

  @smoke
  Scenario: valid ess login
    And user enters valid ess username and password
    When user clicks on login button
    Then ess user is successfully logged in

    # Combine two above Scenarios into one Scenario Outline:

  Scenario Outline: positive user login
    When user enters valid "<UserName>" and "<Password>"
    And user clicks on login button
    Then "<FirstName>" is successfully logged in
    Examples:
      | UserName | Password    | FirstName |
      | Admin    | Hum@nhrm123 | Admin     |
      | Syntax   | Syntax123!  | Syntax    |

  @smoke @regression
  Scenario: login with valid username and invalid password
    And user enters valid username and invalid password
    When user clicks on login button
    Then user see invalid credentials text on login page




