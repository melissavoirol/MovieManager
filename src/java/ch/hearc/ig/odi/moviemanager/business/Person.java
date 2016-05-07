package ch.hearc.ig.odi.moviemanager.business;

import ch.hearc.ig.odi.moviemanager.exception.DuplicateElementException;
import ch.hearc.ig.odi.moviemanager.exception.NotExistingElementException;
import java.util.HashMap;
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
     * @param movie The movie that we want to add in the watched movies list of
     * a person
     * @return The movie added
     * @throws ch.hearc.ig.odi.moviemanager.exception.DuplicateElementException
     */
    public Movie addMovie(Movie movie) throws DuplicateElementException {
        if (this.movies.containsKey(movie.getId())) {
            throw new DuplicateElementException(movie.getId());
        }
        this.movies.put(movie.getId(), movie);
        return movie;
    }

    /**
     * Used to remove a movie from the watched movies list of a person (ex.: if
     * there is an error and we want to remove a movie from the list of movies
     * of a person because he/she actually has never seen it ).
     *
     * @exception NotExistingElementException raise an Not existing element
     * exception when we want to remove a film with an id which doesn't exist in
     * the movies list.
     * @param movie The movie that we want to remove from the watched movies
     * list of a person
     * @return The removed movie
     */
    public Movie removeMovie(Movie movie) throws NotExistingElementException {
        if (!this.movies.containsKey(movie.getId())) {
            throw new NotExistingElementException(movie.getId());
        }
        this.movies.remove(movie.getId());
        return movie;
    }

    /**
     *
     * @return The number of movies that the current person has already seen.
     */
    public Integer numberOfMovies() {
        return this.movies.size();
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
