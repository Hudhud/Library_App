Feature: Add cd

  Scenario: Add a cd successfully
    Given that the administrator is logged in
    And I have a cd with title "Programming song", author "Peter", and signature "Pet22"
    When I add the cd
    Then the cd with title "Programming song", author "Peter", and signature "Pet22" is added to the library

  Scenario: Add a cd when the adminstrator is not logged in
    Given that the administrator is not logged in
    And I have a cd with title "Programming song", author "Peter", and signature "Pet22"
    When I add the cd
    Then I get the error message "Administrator login required"
