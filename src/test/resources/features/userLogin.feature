@DemoTest
Feature: user login
  user profile validation

  @UserLogin
  Scenario Outline: user login
    Given input username "<username>" and password "<password>"
    When submit user login
    Then navigate to welcome page
    Examples:
      | username | password |
      | standard_user | secret_sauce |
      | locked_out_user | secret_sauce |