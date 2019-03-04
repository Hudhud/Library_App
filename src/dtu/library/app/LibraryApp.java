package dtu.library.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import static org.mockito.Mockito.*;
import javax.naming.directory.SearchResult;

public class LibraryApp {

	private boolean isAdminLoggedIn = false;
	private List<Book> bookStorage = new ArrayList<Book>();
	private List<CD> CDStorage = new ArrayList<CD>();
	private List<User> userStorage = new ArrayList<User>();
	private DateServer dateServer = new DateServer();
	private EmailServer emailServer = new EmailServer();
	private Helper helper;

	public List<Book> getBooks() {
		return bookStorage;
	}
	
	public List<CD> getCDs() {
		return CDStorage;
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
	
	public void addCD(CD cd) throws OperationNotAllowedException {
		if (isAdminLoggedIn == false)
			throw new OperationNotAllowedException("Administrator login required");

		getCDs().add(cd);
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

	public void unRegisterUser(User user) throws OperationNotAllowedException {
		if (isAdminLoggedIn == false)
			throw new OperationNotAllowedException("Administrator login required");

		if (!getUsers().contains(user))
			throw new OperationNotAllowedException("User does not exist");

		getUsers().remove(getUsers().indexOf(user));
	}

	public boolean userExists(User user) {
		boolean exists = getUsers().contains(user);
		return exists;
	}

	public void setDateServer(DateServer dateServer) {
		this.dateServer = dateServer;
	}

	public void setEmailServer(EmailServer emailServer) {
		this.emailServer = emailServer;
	}

	public Calendar getDate() {
		return dateServer.getDate();
	}

	public void borrowBook(Book book, User user) throws OperationNotAllowedException {

		if (userHasOverdueBooks(user) == true || userHasFine(user) == true)
			throw new OperationNotAllowedException("You are not eligible to borrow a book");

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

	public boolean userHasFine(User user) {
		return user.userHasFine();
	}

	public int getFineForUser(User user) {
		return user.getUserFine();
	}

	public void userPaysFine(User user) {
		user.payFine();
	}

	public void sendReminder(User user, int books) throws Exception {
		emailServer.sendMail(user, books);
	}

	public List<Book> getUserBorrowedBooks(User user) {
		return user.getBorrowedBooks();
	}
}
