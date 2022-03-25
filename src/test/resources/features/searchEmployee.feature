Feature: Employee Search

  Scenario: Search Employee by id
    Given user is navigated to HRM homepage
    * user is logged with valid admin credentials
    * user navigates to employee list page
    * user enters employee's valid id
    When user clicks on search button
    Then user see employee information is displayed

#  Scenario: Search Employee by name
#    Given user is navigated to HRM homepage
#    * user is logged with valid admin credentials
#    * user navigates to employee list page
#    * user enters employee's valid firstname and lastname
#    When user clicks on search button
#    Then user see employee information is displayed