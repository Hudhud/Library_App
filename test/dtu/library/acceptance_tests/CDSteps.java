package dtu.library.acceptance_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dtu.library.app.Book;
import dtu.library.app.CD;
import dtu.library.app.ErrorMessageHolder;
import dtu.library.app.Helper;
import dtu.library.app.LibraryApp;
import dtu.library.app.MockDateHolder;
import dtu.library.app.OperationNotAllowedException;

public class CDSteps {

	private LibraryApp libraryApp;
	private CD cd;
	private List<CD> cds;
	private ErrorMessageHolder errorMessageHolder;
	private Helper helper;
	private MockDateHolder mockDateHolder;

	public CDSteps(LibraryApp libraryApp, ErrorMessageHolder errorMessageHolder, Helper helper,
			MockDateHolder mockDateHolder) {
		this.libraryApp = libraryApp;
		this.errorMessageHolder = errorMessageHolder;
		this.helper = helper;
		this.mockDateHolder = mockDateHolder;
	}
	
	@Given("I have a cd with title {string}, author {string}, and signature {string}")
	public void i_have_a_cd_with_title_author_and_signature(String title, String author, String signature) {
		cd = new CD(title, author, signature);
	}

	@When("I add the cd")
	public void i_add_the_cd() {
		try {
			libraryApp.addCD(cd);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the cd with title {string}, author {string}, and signature {string} is added to the library")
	public void the_cd_with_title_author_and_signature_is_added_to_the_library(String title, String author, String signature) {
		assertEquals(title, cd.getTitle());
		assertEquals(author, cd.getAuthor());
		assertEquals(signature, cd.getSignature());
		assertTrue(libraryApp.getCDs().contains(cd));
	}

}
