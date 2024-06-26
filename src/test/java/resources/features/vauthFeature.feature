Feature: Login Feature

  Scenario: Successful Login
    Given a user with username "Uktam.Sodikov" and password "Test@123Test" and confirm password "Test@123Test"
    When the user logs in correctly
    Then a valid token is returned

  Scenario: Failed Login due to incorrect password
    Given a user with username "testuser" and incorrect password "InvalidPassword"
    When the user logs in incorrectly
    Then no token is returned

  Scenario: Failed Login due to user not found
    Given a non-existing user with username "nonexistinguser" and password "NonExisting@123"
    When the user logs in non-existed
    Then fallback-token is returned

