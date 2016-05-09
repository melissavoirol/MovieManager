package ch.hearc.ig.odi.moviemanager.presentation.bean;

import ch.hearc.ig.odi.moviemanager.business.Movie;
import ch.hearc.ig.odi.moviemanager.service.Services;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Bean tied to the homePage.xhtml, displays the movies list (ViewScope)
 *
 * @author Melissa Voirol <melissa.voirol@he-arc.ch>
 */
@Named(value = "moviesBean")
@ViewScoped
public class MoviesBean implements Serializable {

    @Inject
    Services services;

    private List<Movie> movies;

    public MoviesBean() {

    }

    /**
     * Initializes the movies bean attribute with the list of movies from the
     * services class
     */
    public void initMovies() {
        this.movies = new ArrayList();
        this.movies.addAll(services.getMoviesList());
    }

    /**
     * Returns a list of movies
     *
     * @return A list of movies
     */
    public List<Movie> getMovies() {
        return movies;
    }

}
