Feature: Unregister user

  Scenario: Unregister user
    Given that the administrator is logged in
    And there is a already a registered user
    And the user does not have any borrowed books
    And the user does not have any fines
    When the administrator unregisters the user
    Then the user is a unregistered user of the library

  Scenario: Unregister a user when not the administrator
    Given that the administrator is not logged in
    When the administrator unregisters the user
    Then I get the error message "Administrator login required"

  Scenario: Unregister a user that is already unregistered
    Given a user is unregistered with the library
    And that the administrator is logged in
    When the administrator unregisters the user again
    Then throw the error "User does not exist"
