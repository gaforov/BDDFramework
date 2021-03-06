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

  @datatable
  Scenario: Adding multiple employees using datatable
    When user enters new employee's details, clicks on save button and verify that employee added successfully
      | FirstName | MiddleName | LastName |
      | John      | W.         | Smith    |
      | Mary      | A.         | Ann      |
      | Michael   | L.         | Williams |

  @excel
  Scenario: Adding multiple employees from excel spreadsheet
    When user enters employee data from "Employee" sheet, then employee is added