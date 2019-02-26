Feature: Add book
#	Description: A book is added to the library
#	Actors: Administrator
#
Scenario: Add a book successfully
 	Given that the administrator is logged in
 	And I have a book with title "Extreme Programming", author "Kent Beck", and signature "Beck99"
 	When I add the book
 	Then the book with title "Extreme Programming", author "Kent Beck", and signature "Beck99" is added to the library
 	
Scenario: Add a book when the adminstrator is not logged in
	Given that the administrator is not logged in
 	And I have a book with title "Extreme Programming", author "Kent Beck", and signature "Beck99"
	When I add the book
	Then I get the error message "Administrator login required"