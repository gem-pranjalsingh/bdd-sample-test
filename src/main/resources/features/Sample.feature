Feature: Sample

  @sampleTag
  Scenario Outline: Successful Login
    Given User is on the login page
    When Enter valid credentials with username "<username>" and password "<password>"
    And Click on login button
    Then User should be logged in successfully
    Examples:
      | username      | password     |
      | standard_user | secret_sauce |

  Scenario Outline: Negative Login
    Given User is on the login page
    When Enter valid credentials with username "<username>" and password "<password>"
    And Click on login button
    Then Verify an error message "<message>"
    Examples:
      | username     | password     | message                                                                   |
      | invalid_user | invalid_pass | Epic sadface: Username and password do not match any user in this service |