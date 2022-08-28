@workflow
Feature: HRM API End to End Workflow

  Description: This feature tests and verifies HRM Web Services
  The workflow consists of the following sequential calls

  Background:
    Given a JWT is generated

  Scenario: Create an employee
    Given a request is prepared to create an employee
    When a POST call is made to create an employee
    Then status code for creating an employee is 201
    And employee is created
    And employee ID is stored as a global variable to be used for other calls.