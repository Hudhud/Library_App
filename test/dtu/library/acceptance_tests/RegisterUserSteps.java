package dtu.library.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.library.app.Address;
import dtu.library.app.ErrorMessageHolder;
import dtu.library.app.LibraryApp;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.User;

public class RegisterUserSteps {

	private User user;
	private Address address;
	private LibraryApp libraryApp;
	private ErrorMessageHolder errorMessageHolder;

	public RegisterUserSteps(LibraryApp libraryApp, ErrorMessageHolder errorMessageHolder) {
		this.libraryApp = libraryApp;
		this.errorMessageHolder = errorMessageHolder;
	}

	@Given("there is a user with CPR {string}, name {string}, e-mail {string}")
	public void there_is_a_user_with_CPR_name_e_mail(String cpr, String name, String email) {
		user = new User(cpr, name, email);
		assertEquals(user.getCPR(), cpr);
		assertEquals(user.getName(), name);
		assertEquals(user.getEmail(), email);
	}

	@Given("the user has address street {string}, post code {int}, and city {string}")
	public void the_user_has_address_street_post_code_and_city(String street, Integer postCode, String city) {
		address = new Address(street, postCode, city);

		assertEquals(address.getStreet(), street);
		assertTrue(address.getPostCode() == postCode);
		assertEquals(address.getCity(), city);

		user.setAddress(address);
		assertTrue(user.getAddress().equals(address));
	}

	@When("the administrator registers the user")
	public void the_administrator_registers_the_user() throws OperationNotAllowedException {
		
		try {
			libraryApp.registerUser(user);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the user is a registered user of the library")
	public void the_user_is_a_registered_user_of_the_library() {
		assertTrue(libraryApp.getUsers().contains(user));
	}

	@Given("a user is registered with the library")
	public void a_user_is_registered_with_the_library() throws OperationNotAllowedException {
		libraryApp.adminLogin("adminadmin");
		libraryApp.registerUser(user);
		
		assertTrue(libraryApp.adminLoggedIn());
		assertTrue(libraryApp.getUsers().contains(user));
	}

	@When("the administrator registers the user again")
	public void the_administrator_registers_the_user_again() {

		try {
			libraryApp.registerUser(user);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		libraryApp.adminLogout();

	}

}
