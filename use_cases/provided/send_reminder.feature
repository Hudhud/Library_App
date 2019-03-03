Feature: send reminder

Scenario: The administrator wants to send a reminder for the overdue book(s)
	Given the administrator is signed in
	And the user has borrowed a book
	And 29 days have passed
	Then send a reminder to the user