package ch.hearc.ig.odi.moviemanager.business;

import ch.hearc.ig.odi.moviemanager.exception.DuplicateElementException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Melissa Voirol <melissa.voirol@he-arc.ch>
 */
public class Movie implements Serializable {

    private Long id;
    private String name;
    private String producer;
    private Map<Long, Person> people;

    /**
     * Mandatory parameterized constructor of the Movie class
     *
     * @param id Unique number of the movie
     * @param name Name of the movie
     * @param producer Producer of the movie
     */
    public Movie(Long id, String name, String producer) {
        this.id = id;
        this.name = name;
        this.producer = producer;
        this.people = new HashMap<>();
    }

    /**
     * Add a new person to the movie and returns an instance of that person
     *
     * @param id Unique number of the person
     * @param fn Firstname of the person
     * @param ln Lastname of the person
     * @return The new person
     * @throws ch.hearc.ig.odi.moviemanager.exception.DuplicateElementException
     */
    public Person addPerson(Long id, String fn, String ln) throws DuplicateElementException {

        Person pers = null;

        if (!people.containsKey(id)) {
            pers = new Person(id, fn, ln);
            people.put(id, pers);
        } else {
            throw new DuplicateElementException("A person with that id already exists");
        }
        return pers;
    }

    /**
     * A commenter...
     *
     * @return
     */
    public Integer getPeopleSize() {
        return people.size();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Map<Long, Person> getPeople() {
        return people;
    }

}
