package ch.hearc.ig.odi.moviemanager.presentation.bean;

import ch.hearc.ig.odi.moviemanager.business.Movie;
import ch.hearc.ig.odi.moviemanager.business.Person;
import ch.hearc.ig.odi.moviemanager.exception.DuplicateElementException;
import ch.hearc.ig.odi.moviemanager.service.Services;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
public class PersonCreateBean implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(PersonCreateBean.class.getName());
    private Person person;
    private Movie movieToAdd;

    @Inject
    Services services;

    public PersonCreateBean() {
    }

    /**
     * Used to save a new
     *
     * @return Success if we saved a new person successfully otherwise error
     */
    public String savePerson() {
        try {
            services.addPerson(this.person);
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
        this.person = new Person();
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
        this.person.getMovies().put(this.movieToAdd.getId(), this.movieToAdd);
    }

    /**
     *
     * @param movie The removed movie from the movies list of the current person
     */
    public void removeMovie(Movie movie) {
        this.person.getMovies().remove(movie.getId());
    }

    /**
     *
     * @return The movies list of the current person, to display them when we
     * are creating the person
     *
     */
    public List<Movie> getMoviesList() {
        return new ArrayList(this.person.getMovies().values());
    }

    /**
     * This method return only movies which aren't yet added in the movies list
     * of the new person.
     *
     * @return
     */
    public List<Movie> getAllMoviesList() {
        List<Movie> lm = services.getMoviesList();
        lm.removeAll(this.person.getMovies().values());
        return lm;
    }

    //Getter and setter methods
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Movie getMovieToAdd() {
        return movieToAdd;
    }

    public void setMovieToAdd(Movie movie) {
        this.movieToAdd = movie;
    }

}
