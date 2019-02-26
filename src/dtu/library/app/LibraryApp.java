package dtu.library.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import static org.mockito.Mockito.*;
import javax.naming.directory.SearchResult;

public class LibraryApp {

	private boolean isAdminLoggedIn = false;
	private List<Book> bookStorage = new ArrayList<Book>();
	private List<User> userStorage = new ArrayList<User>();
	private DateServer dateServer = new DateServer();

	public List<Book> getBooks() {
		return bookStorage;
	}

	public List<User> getUsers() {
		return userStorage;
	}

	public boolean adminLogin(String password) {
		isAdminLoggedIn = password.equals("adminadmin");
		return isAdminLoggedIn;
	}

	public boolean adminLoggedIn() {
		return isAdminLoggedIn;
	}

	public void adminLogout() {
		isAdminLoggedIn = false;
	}

	public void addBook(Book book) throws OperationNotAllowedException {
		if (isAdminLoggedIn == false)
			throw new OperationNotAllowedException("Administrator login required");

		getBooks().add(book);
	}

	public List<Book> search(String searchText) {
		List<Book> searchResult = new ArrayList<Book>();

		if (adminLoggedIn() == true || adminLoggedIn() == false) {

			for (Book book : getBooks()) {
				if (book.getSignature().contains(searchText) || book.getTitle().contains(searchText)
						|| book.getAuthor().contains(searchText)) {
					searchResult.add(book);
				}
			}
		}
		return searchResult;
	}

	public void registerUser(User user) throws OperationNotAllowedException {
		if (isAdminLoggedIn == false)
			throw new OperationNotAllowedException("Administrator login required");

		if (getUsers().contains(user))
			throw new OperationNotAllowedException("User is already registered");

		getUsers().add(user);
	} 

	public void setDateServer(DateServer dateServer) {
		this.dateServer = dateServer;
	}

	public Calendar getDate() {
		System.out.println(dateServer.getDate().getTime());
		return dateServer.getDate();
	}

	public void borrowBook(Book book, User user) throws TooManyBooksException {
		user.borrowBook(book, getDate());
	}

	public boolean userHasOverdueBooks(User user) {
		return user.hasOverdueBooks(getDate());
	}

	public void addBooksToLibrary(List<Book> exampleBooks) throws OperationNotAllowedException {
		boolean adminLoggedIn = adminLoggedIn();
		if (!adminLoggedIn) {
			adminLogin("adminadmin");
		}
		for (Book b : exampleBooks) {
			addBook(b);
		}
		if (!adminLoggedIn) {
			adminLogout();
		}
	}
}
