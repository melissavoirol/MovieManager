package ch.hearc.ig.odi.moviemanager.presentation.bean;

import ch.hearc.ig.odi.moviemanager.business.Person;
import ch.hearc.ig.odi.moviemanager.service.Services;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * The bean used to display the list of people We use a ViewScoped because we
 * need those informations only when we are on the home page.
 *
 * @author boris.klett <boris.klett@he-arc.ch>
 */
@Named
@ViewScoped
public class PeopleDisplayBean implements Serializable {

    @Inject
    Services services;
    private List<Person> people;

    /**
     * Create new instance of PeopleDisplayBean controller
     */
    public PeopleDisplayBean() {
    }

    /**
     * Initialze the current list of people of the PeopleDisplayBean controller with
     * people that we instaciated in the services class
     */
    public void initPeople() {
        this.people = new ArrayList<>();
        this.people.addAll(services.getPeopleList());
    }

    /**
     *
     * @return The current list of people
     */
    public List<Person> getPeople() {
        return this.people;
    }

    /**
     * Used in the faces-config.xml for the navigation rule
     *
     * @return
     */
    public String processHomePage() {
        return "success";
    }

}
