package dtu.library.app;

import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import static org.mockito.Mockito.*;

public class User {

	private String cpr, name, email;

	private Address address;

	private Book book;

	private List<Book> borrowedBooks = new ArrayList<Book>();

	private boolean userHasOverdueBook = false;

	private int overdueBooks;

	private int fine;

	public User(String cpr, String name, String email) {
		this.cpr = cpr;
		this.name = name;
		this.email = email;
	}

	public String getCPR() {
		return cpr;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}

	public void borrowBook(Book book, Calendar date) {
		book.setDateBooked(date);
		borrowedBooks.add(book);
	}

	public boolean hasOverdueBooks(Calendar currentDate) {

		for (Book book : borrowedBooks) {

			long daysDiff = (currentDate.getTime().getTime() - book.getDateBooked().getTime().getTime())
					/ (1000 * 60 * 60 * 24);

			if (daysDiff > 28) {
				userHasOverdueBook = true;
				overdueBooks++;
				fine += 100;
			}
		}
		return userHasOverdueBook;
	}

	public int getUserFine() {
		return fine;
	}

	public boolean userHasFine() {
		if (fine > 0)
			return true;
		return false;
	}

	public void payFine() {
		fine = 0;
	}

	public int getOverdueBooks() {
		return overdueBooks;
	}
	
	public List<Book> getBorrowedBooks(){
		System.out.println("SS "+ borrowedBooks.size());
		return borrowedBooks;
		
	}

}
