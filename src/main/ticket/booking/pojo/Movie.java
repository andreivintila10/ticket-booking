package main.ticket.booking.pojo;

public class Movie {
	private int id;
	private String title;
	private String genre;
	private int year;
	private double rating;

	public Movie() {}

	public Movie(String thisTitle, String thisGenre, int thisYear, double thisRating) {
		title = thisTitle;
		genre = thisGenre;
		year = thisYear;
		rating = thisRating;
	}

	public void setId(int thisId) {
		id = thisId;
	}

	public int getId() {
		return id;
	}

	public void setTitle(String thisTitle) {
		title = thisTitle;
	}

	public String getTitle() {
		return title;
	}

	public void setGenre(String thisGenre) {
		genre = thisGenre;
	}

	public String getGenre() {
		return genre;
	}

	public void setYear(int thisYear) {
		year = thisYear;
	}

	public int getYear() {
		return year;
	}

	public void setRating(double thisRating) {
		rating = thisRating;
	}

	public double getRating() {
		return rating;
	}
	
	public String toString() {
		return id + ". " + title + " (" + year + ") - " + genre + " - " + rating + "/10";
	}
}
