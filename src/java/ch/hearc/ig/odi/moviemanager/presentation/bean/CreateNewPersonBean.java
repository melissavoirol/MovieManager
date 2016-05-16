package ch.hearc.ig.odi.moviemanager.presentation.bean;

import ch.hearc.ig.odi.moviemanager.business.Movie;
import ch.hearc.ig.odi.moviemanager.business.Person;
import ch.hearc.ig.odi.moviemanager.exception.DuplicateElementException;
import ch.hearc.ig.odi.moviemanager.service.Services;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author boris.klett
 */
@Named
@SessionScoped
public class CreateNewPersonBean implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(CreateNewPersonBean.class.getName());
    private Long id;
    private String lastName;
    private String firstName;
    private Movie movieToAdd;
    private Map<Long, Movie> movies;

    @Inject
    Services services;

    public CreateNewPersonBean() {
        movies = new HashMap<>();
    }

    /**
     * Used to save a new
     *
     * @return Success if we saved a new person successfully otherwise error
     */
    public String savePerson() {
        Person p = new Person();
        p.setId(this.id);
        p.setLastName(this.lastName);
        p.setFirstName(this.firstName);
        p.setMovies(this.movies);
        try {
            services.addPerson(p);
        } catch (DuplicateElementException ex) {
            LOGGER.log(Level.SEVERE, "ERROR! occured when we save a new person {0}", ex);
            return "error";
        }
        return "success";
    }

    /**
     *
     * @return Success if the changin to the create a new person page made
     * successfully otherwise return Error
     */
    public String processPageCreatePerson() {
        //Due to a SessionScope probled, I found this method to reinitialize the
        //session attribute I didn't find a better method

        this.id = null;
        this.firstName = null;
        this.lastName = null;
        this.movieToAdd = null;
        this.movies = null;
        return "success";
    }

    /**
     * Use to add a film from the list in the class services to the movies list
     * of the current person.
     */
    public void addMovie() {
        if (this.movieToAdd == null) {
            return;
        }
        if (this.movies == null) {
            this.movies = new HashMap<>();
        }
        this.movies.put(this.movieToAdd.getId(), this.movieToAdd);
    }

    /**
     *
     * @param movie The removed movie from the movies list of the current person
     */
    public void removeMovie(Movie movie) {

        this.movies.remove(movie.getId());

    }

    /**
     *
     * @return The movies list of the current person, to display them when we
     * are creating the person
     *
     */
    public List<Movie> getMoviesList() {
        try {
            return new ArrayList(movies.values());
        } catch (NullPointerException ex) {
            return new ArrayList();
        }
    }

    //Getter and setter methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Movie getMovieToAdd() {
        return movieToAdd;
    }

    public void setMovieToAdd(Movie movie) {
        this.movieToAdd = movie;
    }

    public Map<Long, Movie> getMovies() {
        return movies;
    }

    public void setMovies(Map<Long, Movie> movies) {
        this.movies = movies;
    }

}
