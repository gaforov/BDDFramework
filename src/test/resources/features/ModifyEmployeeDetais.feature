Feature: Add or edit employee details

  Scenario: Admin should be able to modify employee personal details
  """
  Task/US (User Story): Store data in Excel then pull them from excel and store them on HRM website
  Driver's License Number
  License Expiry Date
  SSN Number
  SIN Number
  Gender
  Marital Status
  Nationality
  Date of Birth (yyyy-mm-dd)
  Smoker
  Military Service
  """
    When user is logged with valid admin credentials
    And user navigates to employee list page
    And user searches employee by employeeId and when found clicks on it
    And user clicks on edit button to modify employee details from personal details page
    And user enters employee data from "EmployeeDetails" sheet, then clicks save button

