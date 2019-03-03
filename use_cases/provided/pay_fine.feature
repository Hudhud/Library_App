Feature: pay fine

Scenario: user pays his fine
Given User has an overdue book
When 29 or more days have passed
Then the user pays his fine