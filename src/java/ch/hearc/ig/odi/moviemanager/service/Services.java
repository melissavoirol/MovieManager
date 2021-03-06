package ch.hearc.ig.odi.moviemanager.service;

import ch.hearc.ig.odi.moviemanager.business.Movie;
import ch.hearc.ig.odi.moviemanager.business.Person;
import ch.hearc.ig.odi.moviemanager.exception.DuplicateElementException;
import ch.hearc.ig.odi.moviemanager.exception.NotExistingElementException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

/**
 * Classe de services qui stocke les données de l'application dans la session de
 * l'utilisateur. Cette classe est gérée par CDI et peut donc être injectée dans
 * les autres classes Java.
 *
 * @author julien.plumez
 */
@SessionScoped
@Stateful
public class Services implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(Services.class.getName());
    private Map<Long, Person> people;
    private Map<Long, Movie> movies;

    /**
     * Initialise la classe de services et crée 6 personnes et 9 films pour
     * avoir des données de test.
     *
     */
    public Services() {
        try {

            people = new LinkedHashMap<>();
            people.put(1l, new Person(1l, "Lara", "Clette"));
            people.put(2l, new Person(2l, "Homer", "Dalors"));
            people.put(3l, new Person(3l, "Sarah", "Vigote"));
            people.put(4l, new Person(4l, "Pierre", "Oglyphe"));
            people.put(5l, new Person(5l, "John", "Doeuf"));
            people.put(6l, new Person(6l, "Cécile", "Icone"));

            // Liste tirée de http://www.allocine.fr/film/meilleurs/
            movies = new LinkedHashMap<>();
            movies.put(1l, new Movie(1l, "Forrest Gump", "Robert Zemeckis"));
            movies.put(2l, new Movie(2l, "La ligne verte", "Frank Darabont"));
            movies.put(3l, new Movie(3l, "Django Unchained", "Quentin Tarantino"));
            movies.put(4l, new Movie(4l, "Gran Torino", "Clint Eastwood"));
            movies.put(5l, new Movie(5l, "La liste de Schindler", "Steven Spielberg"));
            movies.put(6l, new Movie(6l, "The Dark Knight", "Christopher Nolan"));
            movies.put(7l, new Movie(7l, "Le Parrain", "Francis Ford Coppola"));
            movies.put(8l, new Movie(8l, "Pulp Fiction", "Quentin Tarantino"));
            movies.put(9l, new Movie(9l, "Le seigneur des anneaux, le retour du roi", "Peter Jackson"));

            people.get(1l).addMovie(movies.get(1l));
            people.get(1l).addMovie(movies.get(3l));
            people.get(1l).addMovie(movies.get(5l));
            people.get(1l).addMovie(movies.get(6l));
            people.get(2l).addMovie(movies.get(7l));
            people.get(2l).addMovie(movies.get(8l));
            people.get(2l).addMovie(movies.get(9l));
            people.get(3l).addMovie(movies.get(2l));
            people.get(3l).addMovie(movies.get(4l));
            people.get(3l).addMovie(movies.get(5l));
            people.get(4l).addMovie(movies.get(6l));
            people.get(4l).addMovie(movies.get(8l));
            people.get(4l).addMovie(movies.get(1l));
            people.get(4l).addMovie(movies.get(2l));
            people.get(4l).addMovie(movies.get(3l));
            people.get(4l).addMovie(movies.get(5l));
            people.get(4l).addMovie(movies.get(7l));
            people.get(5l).addMovie(movies.get(3l));
            people.get(5l).addMovie(movies.get(4l));
            people.get(5l).addMovie(movies.get(6l));
            people.get(6l).addMovie(movies.get(7l));
            people.get(6l).addMovie(movies.get(9l));
            people.get(6l).addMovie(movies.get(1l));
            people.get(6l).addMovie(movies.get(2l));

            for (Map.Entry<Long, Movie> entryMovie : movies.entrySet()) {
                Movie m = entryMovie.getValue();

                for (Map.Entry<Long, Person> entryPerson : people.entrySet()) {
                    Person p = entryPerson.getValue();

                    if (p.getMovies().get(m.getId()) != null) {
                        movies.get(m.getId()).addPerson(p.getId(), p.getFirstName(), p.getLastName());
                    }
                }
            }

        } catch (DuplicateElementException ex) {
            LOGGER.log(Level.SEVERE, "ERROR!! occuring when we add a new element: {0}", ex);
        }
    }

    /**
     * Retourne une List contenant toutes les personnes du système. Utile pour
     * l'affichage des personnes dans les facelets
     *
     * @return Une List contenant toutes les personnes du système.
     */
    public List<Person> getPeopleList() {
        return new ArrayList(people.values());
    }

    /**
     *
     * @param newPerson The person that we want to add
     * @throws DuplicateElementException
     */
    public void addPerson(Person newPerson) throws DuplicateElementException {
        if (this.people.containsKey(newPerson.getId())) {
            throw new DuplicateElementException("A person with the id: " + newPerson.getId() + " already exist");
        }
        this.people.put(newPerson.getId(), newPerson);
        for (Map.Entry<Long, Movie> entryMovie : newPerson.getMovies().entrySet()) {
            Movie m = entryMovie.getValue();
            if (this.movies.get(m.getId()) != null) {
                movies.get(m.getId()).addPerson(newPerson.getId(), newPerson.getFirstName(), newPerson.getLastName());
            }
        }

    }

    /**
     * Retourne une List contenant tous les films du système Utile pour
     * l'affichage des films dans les facelets
     *
     * @return Une List contenant tous les films du système
     */
    public List<Movie> getMoviesList() {
        return new ArrayList(movies.values());
    }

    /**
     * Creates a new movie
     *
     * @param id Unique number of the movie
     * @param name Name of the movie
     * @param producer Producer of the movie
     * @throws DuplicateElementException
     */
    public void saveMovie(Long id, String name, String producer) throws DuplicateElementException {
        movies.put(id, new Movie(id, name, producer));
    }

    /**
     * Retourne un objet personne pour pouvoir afficher ou modifier ses
     * attributs
     *
     * @param person
     * @return
     * @throws NotExistingElementException
     */
    public Person getAPerson(Person person) throws NotExistingElementException {
        if (this.people.containsKey(person.getId())) {
            return this.people.get(person.getId());
        }
        throw new NotExistingElementException("Nobody exist with the id: " + person.getId());
    }

    /**
     * Retourne la liste des films regardés par la personnes passées en
     * paramètre.
     *
     * @param personId
     * @return
     */
    public List<Movie> getAPersonMovies(Long personId) {
        List<Movie> listMovies = new ArrayList<>();
        for (Map.Entry<Long, Movie> entry : this.movies.entrySet()) {
            Movie m = entry.getValue();
            if (m.getPeople().get(personId.intValue()) != null) {
                listMovies.add(m);
            }
        }

        return listMovies;
    }

    /**
     *
     * @param idMovie
     * @return A movie
     */
    public Movie getMovieById(Long idMovie) {
        return this.movies.get(idMovie);
    }
}
