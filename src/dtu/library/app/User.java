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

	private Medium medium;

	private List<Medium> borrowedMedia = new ArrayList<Medium>();

	private boolean userHasOverdueMedium = false;

	private int overdueMedia;

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

	public void borrowMedium(Medium medium, Calendar date) {
		medium.setDateBooked(date);
		borrowedMedia.add(medium);
	}

	public boolean hasOverdueMedia(Calendar currentDate) {
		System.out.println(currentDate.getTime());
		for (Medium medium : borrowedMedia) {

			long daysDiff = (currentDate.getTime().getTime() - medium.getDateBooked().getTime().getTime()) / 1000;

			if (daysDiff > 86400 * 28) {
				userHasOverdueMedium = true;
				overdueMedia++;
				fine += 100;
			}
		}
		return userHasOverdueMedium;
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

	public int getOverdueMedia() {
		return overdueMedia;
	}
	
	public List<Medium> getBorrowedMedia(){
		return borrowedMedia;		
	}

}
