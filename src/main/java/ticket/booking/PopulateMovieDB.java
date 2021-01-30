package ticket.booking;

import java.util.ArrayList;
import java.util.List;

import ticket.booking.dao.MovieDAO;
import ticket.booking.dao.MovieImpl;
import ticket.booking.pojo.Movie;

public class PopulateMovieDB {

	public static void main(String[] args) {
		List<Movie> movies = new ArrayList<Movie>();
		movies.add(new Movie("The Terminator", "Action, Sci-Fi", 1984, 8.0));
		movies.add(new Movie("Titanic", "Drama, Romance", 1997, 7.8));
		movies.add(new Movie("Fight Club", "Drama", 1999, 8.8));
		movies.add(new Movie("Shrek", "Animation, Adventure, Comedy", 2001, 7.8));
		movies.add(new Movie("Harry Potter and the Prisoner of Azkaban", "Adventure, Family, Fantasy", 2004, 7.9));
		movies.add(new Movie("Avatar", "Action, Adventure, Fantasy", 2009, 7.8));
		movies.add(new Movie("Law Abiding Citizen", "Action, Crime, Drama", 2009, 7.8));
		movies.add(new Movie("In Time", "Action, Sci-Fi, Thriller", 2011, 6.7));
		movies.add(new Movie("The Avengers", "Action, Adventure, Sci-Fi", 2012, 8.0));
		movies.add(new Movie("Ouija - Origin of Evil", "Drama, Horror, Mystery", 2016, 6.1));

		MovieDAO movieDAO = new MovieImpl();
		for (Movie movie : movies) {
			movieDAO.insertMovie(movie);
		}
	}
}