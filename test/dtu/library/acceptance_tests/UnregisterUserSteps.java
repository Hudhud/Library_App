package dtu.library.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.library.app.Address;
import dtu.library.app.ErrorMessageHolder;
import dtu.library.app.Helper;
import dtu.library.app.LibraryApp;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.User;

public class UnregisterUserSteps {
	
	private User user;
	private Address address;
	private LibraryApp libraryApp;
	private ErrorMessageHolder errorMessageHolder;
	private Helper helper;
	
	public UnregisterUserSteps(LibraryApp libraryApp, ErrorMessageHolder errorMessageHolder) {
		this.libraryApp = libraryApp;
		this.errorMessageHolder = errorMessageHolder;
	}

	@Given("there is a already a registered user")
	public void there_is_a_already_a_registred_user() throws OperationNotAllowedException {
		user = new User("55555", "uss", "mail");
		libraryApp.registerUser(user);
	}
	
	@Given("the user does not have any borrowed books")
	public void the_user_does_not_have_any_borrowed_books() {
	    assertTrue(libraryApp.getUserBorrowedMedia(user).size() == 0);
	}

	@Given("the user does not have any fines")
	public void the_user_does_not_have_any_fines() {
	    assertTrue(libraryApp.getFineForUser(user) == 0);
	}
	
	@When("the administrator unregisters the user")
	public void the_administrator_unregisters_the_user() {
	    try {
			libraryApp.unRegisterUser(user);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the user is a unregistered user of the library")
	public void the_user_is_a_unregistered_user_of_the_library() {
	    assertTrue(!libraryApp.userExists(user));
	}
	

	@Given("a user is unregistered with the library")
	public void a_user_is_unregistered_with_the_library() {
		user = new User("55555", "uss", "mail");
	    assertTrue(!libraryApp.userExists(user));
	}

	@When("the administrator unregisters the user again")
	public void the_administrator_unregisters_the_user_again() {
		try {
			libraryApp.unRegisterUser(user);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("throw the error {string}")
	public void throw_the_error(String errorM) {
	    assertEquals(errorMessageHolder.getErrorMessage(), errorM);
	}
}
