Feature: Dashboard

  Scenario: Dashboard view for admin user
    When user enters valid admin username and password
    And user clicks on login button
    Then user should see dashboard menu displayed
      | Admin | PIM | Leave | Time | Recruitment | Performance | Dashboard | Directory |
