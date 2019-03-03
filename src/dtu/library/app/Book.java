package dtu.library.app;

import java.util.Calendar;
import java.util.Date;

public class Book {

	private String title, author, signature;
	private Calendar calendar;

	public Book(String title, String author, String signature) {
		this.title = title;
		this.author = author;
		this.signature = signature;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getSignature() {
		return signature;
	}

	public void setDateBooked(Calendar date) {
		calendar = date; 	
	}
	
	public Calendar getDateBooked() {
		return calendar;
		
	}
}
