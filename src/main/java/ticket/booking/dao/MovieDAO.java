package ticket.booking.dao;

import java.util.List;

import ticket.booking.pojo.Movie;

public interface MovieDAO {
	public List<Movie> getAllMovies();
	public Movie getMovie(int id);
	public Movie getMovieByTitle(String name);
	public boolean insertMovie(Movie movie);
	public boolean updateMovie(Movie movie);
	public boolean deleteMovie(Movie movie);
}