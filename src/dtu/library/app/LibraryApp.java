package dtu.library.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import static org.mockito.Mockito.*;
import javax.naming.directory.SearchResult;

public class LibraryApp {

	private boolean isAdminLoggedIn = false;
	private List<Medium> mediumStorage = new ArrayList<Medium>();
	private List<CD> CDStorage = new ArrayList<CD>();
	private List<User> userStorage = new ArrayList<User>();
	private DateServer dateServer = new DateServer();
	private EmailServer emailServer = new EmailServer();
	private Helper helper;

	public List<Medium> getMedia() {
		return mediumStorage;
	}
	
	public List<Medium> getCDs() {
		return getMedia();
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

	public void addMedium(Medium medium) throws OperationNotAllowedException {
		if (isAdminLoggedIn == false)
			throw new OperationNotAllowedException("Administrator login required");

		getMedia().add(medium);
	}
	
	public List<Medium> search(String searchText) {
		List<Medium> searchResult = new ArrayList<Medium>();

		if (adminLoggedIn() == true || adminLoggedIn() == false) {

			for (Medium medium : getMedia()) {
				if (medium.getSignature().contains(searchText) || medium.getTitle().contains(searchText)
						|| medium.getAuthor().contains(searchText)) {
					searchResult.add(medium);
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

	public void borrowMedium(Medium medium, User user) throws OperationNotAllowedException {

		if (userHasOverdueMedia(user) == true || userHasFine(user) == true)
			throw new OperationNotAllowedException("You are not eligible to borrow a book");

		user.borrowMedium(medium, getDate());
	}

	public boolean userHasOverdueMedia(User user) {
		return user.hasOverdueMedia(getDate());
	}

	public void addMediaToLibrary(List<Medium> exampleMedia) throws OperationNotAllowedException {
		boolean adminLoggedIn = adminLoggedIn();
		if (!adminLoggedIn) {
			adminLogin("adminadmin");
		}
		for (Medium b : exampleMedia) {
			addMedium(b);
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

	public void sendReminder(User user, int media) throws Exception {
		emailServer.sendMail(user, media);
	}

	public List<Medium> getUserBorrowedMedia(User user) {
		return user.getBorrowedMedia();
	}
}
