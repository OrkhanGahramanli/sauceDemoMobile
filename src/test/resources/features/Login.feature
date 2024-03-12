Feature: Login

  @Login
  Scenario: Successful login
    Given User is in app
    When User clicks "burgerMenu" button
    And User clicks "Log In" button from dropdown
    And User fills "bob@example.com" in "username" input field
    And User fills "10203040" in "password" input field
    And User clicks "loginBtn" button
    Then User should be navigated to HomePage

    @UnSuccessLogin
    Scenario Outline: Unsuccessful login with invalid credentials/ Scenario name: "<CaseName>"
      Given User is in app
      When User clicks "burgerMenu" button
      And User clicks "Log In" button from dropdown
      And User fills "<userName>" in "username" input field
      And User fills "<password>" in "password" input field
      And User clicks "loginBtn" button
      Then User should get "<error>" message

      Examples:
        | CaseName                      | userName        | password | error                                                       |
        | Invalid userName              | orxan           | 10203040 | Provided credentials do not match any user in this service. |
        | Invalid password              | bob@example.com | 123456   | Provided credentials do not match any user in this service. |
        | Empty username                |                 | 10203040 | Username is required                                        |
        | Empty password                | bob@example.com |          | Password is required                                        |
        | Invalid username and password | orxan           | 123456   | Provided credentials do not match any user in this service. |
        | Empty username and password   |                 |          | Username is required                                        |

  @Logout
  Scenario: Successful logout
    Given User is in app
    When User clicks "burgerMenu" button
    And User clicks "Log In" button from dropdown
    And User fills "bob@example.com" in "username" input field
    And User fills "10203040" in "password" input field
    And User clicks "loginBtn" button
    And User clicks "burgerMenu" button
    And User clicks "Log Out" button from dropdown
    And User clicks "logOutBtn" button on pop-up window
    Then User should get "You are successfully logged out." message
