@searchEmployee
Feature: Search existing employee

  Background:
    Given user is logged with valid admin credentials
    And user navigates to employee list page

  Scenario: Search Employee by id
    And user enters existing employee's id
    When user clicks on search button
    Then user see employee information is displayed

  Scenario: Search Employee by name
    And user enters existing employee's firstname and lastname
    When user clicks on search button
    Then user see employee information is displayed