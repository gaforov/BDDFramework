Feature: Add new employee

  Background:
    Given user is logged with valid admin credentials
    And user navigates to AddEmployeePage


  Scenario: Add employee with first name and lastname
    And user enters new employee's firstname and lastname
    When user clicks on save button
    Then employee is added successfully

  Scenario: Add employee without employee Id
    And user enters new employee's firstname and lastname
    And user deletes employee id
    When user clicks on save button
    Then employee is added successfully

  Scenario: Add employee with credentials
    And user enters new employee's firstname and lastname
    And user clicks on create login checkbox
    And user enters new employee's login credentials
    When user clicks on save button
    Then employee is added successfully