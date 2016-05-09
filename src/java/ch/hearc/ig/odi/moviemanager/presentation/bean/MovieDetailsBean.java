package ch.hearc.ig.odi.moviemanager.presentation.bean;

import ch.hearc.ig.odi.moviemanager.business.Movie;
import ch.hearc.ig.odi.moviemanager.business.Person;
import ch.hearc.ig.odi.moviemanager.service.Services;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Bean tied to the movieDetails.xhtml page, displays the details of a movie
 *
 * @author Melissa Voirol <melissa.voirol@he-arc.ch>
 */
@Named(value = "movieDetailsBean")
@SessionScoped
public class MovieDetailsBean implements Serializable {

    @Inject
    Services services;

    private Movie movie;

    public MovieDetailsBean() {

    }

    /**
     * Method that receives the movie to show on the page
     *
     * @param mov The movie to show
     * @return "show" if the movie is valid, "error" if the parameter is null
     */
    public String showMovie(Movie mov) {
        if (movie != null) {
            movie = mov;
            return "show";
        } else {
            movie = null;
            return "error";
        }
    }

    /**
     * Returns all the people who have seen the selected movie, an empty
     * ArrayList if the movie is not valid
     *
     * @return All the people
     */
    public List<Person> getPeople() {
        if (movie == null) {
            return new ArrayList();
        }
        return new ArrayList(movie.getPeople().values());
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

}
