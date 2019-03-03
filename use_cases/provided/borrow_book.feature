Feature: borrow book

  Scenario: Borrow a book while having overdue book(s)
    Given the user has overdue book(s)
    When the user borrows a book
    Then the user gets an error message "You are not eligible to borrow a book"

  Scenario: Borrow a book while having unpaid fine(s)
    Given the user has a fine(s) of 100kr
    When the user borrows a book
    Then the user gets an error message "You are not eligible to borrow a book"
