Feature: Add new employee

  Background:
    Given user is logged with valid admin credentials
    And user navigates to AddEmployeePage


  Scenario: Add employee with first name and lastname
    And user enters new employee's "John" and "Smith"
    When user clicks on save button
    Then "John Smith" is added successfully

  Scenario: Add employee without employee Id
    And user enters new employee's "John" and "Smith"
    And user deletes employee id
    When user clicks on save button
    Then "John Smith" is added successfully

  Scenario: Add employee with credentials
    And user enters new employee's "John" and "Smith"
    And user clicks on create login checkbox
    And user enters new employee's login credentials
    When user clicks on save button
    Then "John Smith" is added successfully

  Scenario Outline: Adding multiple employees
    And user enters new employee's "<Firstname>" and "<Lastname>"
    And user clicks on save button
    Then "<Firstname and Lastname>" is added successfully
    Examples:
      | Firstname | Lastname | Firstname and Lastname |
      | John      | Smith    | John Smith             |
      | Mary      | Ann      | Mary Ann               |
      | Michael   | Williams | Michael Williams       |

