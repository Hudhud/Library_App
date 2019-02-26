package dtu.library.acceptance_tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.library.app.LibraryApp;

public class LoginLogoutSteps {

	private LibraryApp libraryApp;
	private String password;

	/*
	 * Note that the constructor is apparently never called, but there are no null
	 * pointer exceptions regarding that libraryApp is not set. When creating the
	 * LoginSteps object, the Cucumber libraries are using that constructor with an
	 * object of class LibraryApp as the default.
	 * 
	 * This also holds for all other step classes that have a similar constructor.
	 * In this case, the <b>same</b> object of class LibraryApp is used as an
	 * argument. This provides an easy way of sharing the same object, in this case
	 * the object of class LibraryApp, among all step classes.
	 * 
	 * This principle is called <em>dependency injection</em>. More information can
	 * be found in the "Cucumber for Java" book available online from the DTU Library.
	 */
	public LoginLogoutSteps(LibraryApp libraryApp) {
		this.libraryApp = libraryApp;
	}

	@Given("^that the administrator is not logged in$")
	public void thatTheAdministratorIsNotLoggedIn() throws Exception {
		assertFalse(libraryApp.adminLoggedIn());
	}

	@Given("^the password is \"([^\"]*)\"$")
	public void thePasswordIs(String password) throws Exception {
		this.password = password;
	}

	@Then("^the administrator login succeeds$")
	public void theAdministratorLoginSucceeds() throws Exception {
		assertTrue(libraryApp.adminLogin(password));
	}

	@Then("^the adminstrator is logged in$")
	public void theAdminstratorIsLoggedIn() throws Exception {
		assertTrue(libraryApp.adminLoggedIn());
	}

//	@Then("^the administrator logs in successfully$")
//	public void theAdministratorLoginsSuccessfully() throws Exception {
//	}

	@Then("^the administrator login fails$")
	public void theAdministratorLoginFails() throws Exception {
		assertFalse(libraryApp.adminLogin(password));
	}

	@Then("^the administrator is not logged in$")
	public void theAdministratorIsNotLoggedIn() throws Exception {
		assertFalse(libraryApp.adminLoggedIn());
	}

	@Given("^that the administrator is logged in$")
	public void thatTheAdministratorIsLoggedIn() throws Exception {
		assertTrue(libraryApp.adminLogin("adminadmin"));
	}

	@When("^the administrator logs out$")
	public void theAdministratorLogsOut() throws Exception {
		libraryApp.adminLogout();
	}
}
