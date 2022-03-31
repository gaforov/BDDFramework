@sprint5 @login
Feature: Login positive and negative

  @smoke @testingNow
  Scenario Outline: happy path user login
    When user enters valid "<UserName>" and "<Password>"
    And user clicks on login button
    Then "<FirstName>" is successfully logged in

    Examples:
      | UserName | Password    | FirstName |
      | Admin    | Hum@nhrm123 | Admin     |
#      | Syntax   | Syntax123!  | Mike      |

  @regression
  Scenario Outline: negative logins and error messages validation
    When user enters invalid "<UserName>" andOr "<Password>"
    And user clicks on login button
    Then user should see correct "<error message>" displayed

    Examples:
      | UserName | Password | error message            |
      | Admin    | admin12  | Invalid credentials      |
      | Admi     | admin123 | Invalid credentials      |
      | Admi     | admin12  | Invalid credentials      |
      |          | admin123 | Username cannot be empty |
      | Admin    |          | Password cannot be empty |
      |          |          | Username cannot be empty |

