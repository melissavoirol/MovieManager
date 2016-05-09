package ch.hearc.ig.odi.moviemanager.presentation.bean;

import ch.hearc.ig.odi.moviemanager.business.Movie;
import ch.hearc.ig.odi.moviemanager.service.Services;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Bean tied to the homePage.xhtml, displays the movies list
 *
 * @author Melissa Voirol <melissa.voirol@he-arc.ch>
 */
@Named(value = "moviesBean")
@RequestScoped
public class MoviesBean implements Serializable {

    @Inject
    Services services;

    public MoviesBean() {

    }

    /**
     * Returns a list of movies
     *
     * @return A list of movies
     */
    public List<Movie> getMovies() {
        return services.getMoviesList();
    }

}
