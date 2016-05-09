package ch.hearc.ig.odi.moviemanager.presentation.bean;

import ch.hearc.ig.odi.moviemanager.business.Movie;
import ch.hearc.ig.odi.moviemanager.business.Person;
import ch.hearc.ig.odi.moviemanager.exception.NotExistingElementException;
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
 * It is a been controller used to display a person's detail. Here we chose to
 * use a session scoped because we thing that it easier to manipulate an session
 * instantiated object (person) and we can't use a View scoped nieder a request
 * scope because first we'll loose the object (person) before to display it.
 *
 * @author boris.klett <boris.klett@he-arc.ch>
 */
@Named
@SessionScoped
public class PersonDetailBean implements Serializable {

    @Inject
    Services services;

    private static final Logger LOGGER = Logger.getLogger(PersonDetailBean.class.getName());
    private Long id;
    private String firstName;
    private String lastName;
    private Map<Long, Movie> movies;

    /**
     * Create new instance of PersonDetailBean controller
     *
     */
    public PersonDetailBean(){
    }

    /**
     * This method allows to instanciate the person that we want to diplay the
     * details.
     *
     * @param person The person that we want to instanciate in the session.
     * @return Success or Error if there is an error or no.
     */
    public String getAPerson(Person person) {
        try {
            Person p = services.getAPerson(person);
            this.id = p.getId();
            this.firstName = p.getFirstName();
            this.lastName = p.getLastName();
            this.movies = new HashMap<Long, Movie>();
            this.movies.putAll(p.getMovies());

        } catch (NotExistingElementException ex) {
            LOGGER.log(Level.SEVERE, "ERROR! occured when we load a person {0}", ex);
            return "error";
        }
        return "success";
    }

    /**
     * return the watched movies list
     *
     * @return
     */
    public List<Movie> getMoviesList() {
        //return services.getAPersonMovies(this.id);
        return new ArrayList(movies.values());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
