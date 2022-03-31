@searchEmployee @regression
Feature: Search existing employee

  Background:
    Given user is logged with valid admin credentials
    And user navigates to employee list page

  Scenario: Search Employee by id
    And user enters existing employee's id "27335A"
    When user clicks on search button
    Then user see employee with Id "27335A" is displayed

  Scenario: Search Employee by name
    And user enters existing employee's "moazzam" and "sadiq"
    When user clicks on search button
    Then user see employee with Id "27335A" is displayed