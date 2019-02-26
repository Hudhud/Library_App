Feature: Search books 
#	Description: A user (either an ordinary user or the administrator) searches for books by 
#				 providing a substring of either the title, author, or signature field
#	Actors: user
#
Background: The library has a set of books 
	Given that the administrator is logged in 
	And these books are contained in the library 
		| Extreme Programming | Kent Beck | Beck99 |
		| Test Driven Development | Kent Beck | Beck02 |
		| Lean Software Development | Mary Poppendieck and Tom Poppendieck | Pop07 |
		| Cucumber for Java | Seb Rose | Rose11 |
	And the administrator logs out 
	
Scenario: Searching for a substring of the signature 
	Given that the administrator is not logged in 
	When I search for the text "99" 
	Then I find the book with signature "Beck99" 
	
Scenario: Searching for a substring of the title 
	Given that the administrator is not logged in 
	When I search for the text "Extreme" 
	Then I find the book with signature "Beck99" 
	
Scenario: Searching for a substring of the author 
	Given that the administrator is not logged in 
	When I search for the text "Seb" 
	Then I find the book with signature "Rose11" 
	
Scenario: Searching also works when the administrator is logged in 
	Given that the administrator is logged in 
	When I search for the text "Seb" 
	Then I find the book with signature "Rose11" 
	
Scenario: No books match the criteria 
	When I search for the text "Ian" 
	Then I don't find any book 
	
Scenario: Find more than one book 
	When I search for the text "Beck" 
	Then I find a book with signatures "Beck99" and "Beck02" 
	
	