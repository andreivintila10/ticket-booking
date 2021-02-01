package ticket.booking.pojo;

public class Movie {
	private int id;
	private String title;
	private String genre;
	private int year;
	private double rating;

	// Empty constructor.
	public Movie() {
	    /* void */
	}

	// Parametrised constructor overload.
	public Movie(String thisTitle, String thisGenre, int thisYear, double thisRating) {
		title = thisTitle;
		genre = thisGenre;
		year = thisYear;
		rating = thisRating;
	}

	// Mutator method.
	public void setId(int thisId) {
		id = thisId;
	}

	// Accessor method.
	public int getId() {
		return id;
	}

	// Mutator method.
	public void setTitle(String thisTitle) {
		title = thisTitle;
	}

	// Accessor method.
	public String getTitle() {
		return title;
	}

	// Mutator method.
	public void setGenre(String thisGenre) {
		genre = thisGenre;
	}

	// Accessor method.
	public String getGenre() {
		return genre;
	}

	// Mutator method.
	public void setYear(int thisYear) {
		year = thisYear;
	}

	// Accessor method.
	public int getYear() {
		return year;
	}

	// Mutator method.
	public void setRating(double thisRating) {
		rating = thisRating;
	}

	// Accessor method.
	public double getRating() {
		return rating;
	}

	// Method toString.
	public String toString() {
		return id + ". " + title + " (" + year + ") - " + genre + " - " + rating + "/10";
	}
}
