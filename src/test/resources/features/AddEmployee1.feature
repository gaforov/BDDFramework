Feature: Add employees using Scenario Outline

  @regression @testingNow
  Scenario Outline: Adding multiple employees
    Given user is logged with valid admin credentials
    And user navigates to AddEmployeePage
    And user enters new employee's "<FirstName>", "<MiddleName>", and "<LastName>"
    And user clicks on save button
    Then "<FirstName, MiddleName and LastName>" is added successfully
    Examples:
      | FirstName | MiddleName | LastName | FirstName, MiddleName and LastName |
      | John      | A.         | Smith    | John A. Smith                      |
#      | Mary      | J.         | Ann      | Mary J. Ann                        |
#      | Michael   | R.         | Williams | Michael R. Williams                |