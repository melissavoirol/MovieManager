package ch.hearc.ig.odi.moviemanager.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Class business (personne);
 *
 * @author boris.klett <boris.klett@he-arc.ch>
 */
public class Person {

    private Long identifiant;
    private String firstName;
    private String lastName;
    private Map<Long, Movie> movies = new HashMap<Long, Movie>();

    /**
     * Constructor of the Person class, not parameterized
     */
    public Person() {
    }

    public Person(Long identifiant, String firstName, String lastName, Map<Long, Movie> movies) {
        this.identifiant = identifiant;
        this.firstName = firstName;
        this.lastName = lastName;
        this.movies = movies;
    }

    public Person(Long identifiant, String firstName, String lastName) {
        this.identifiant = identifiant;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Used to add a movie in the watched movies list of a person
     *
     * @param movie The movie that we want to add in the watched movies list of a person
     * @return The movie added
     */
    public Movie addMovie(Movie movie) {
        if (this.movies.containsKey(movie.getId())) {
            //Raise une exception unique
            //throw new UniqueException(movie.getId());
            return null;
        }
        this.movies.put(movie.getId(), movie);
        return movie;
    }

    /**
     * Used to remove a movie from the watched movies list of a person (ex.: if there is an error and 
     * we want to remove a movie from the list of movies of a person because he/she actually has never seen it ).
     * 
     * @param movie The movie that we want to remove from the watched movies list of a person 
     * @return The removed movie
     */
    public Movie removeMovie(Movie movie) {
        if (!this.movies.containsKey(movie.getId())) {
            //Raise une exception n'existe pas
            //throw new NotExistException(movie.getId());
            return null;
        }
        this.movies.remove(movie.getId());
        return movie;
    }

    public Long getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(Long identifiant) {
        this.identifiant = identifiant;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Map<Long, Movie> getMovies() {
        return movies;
    }

    public void setMovies(Map<Long, Movie> movies) {
        this.movies = movies;
    }

}
