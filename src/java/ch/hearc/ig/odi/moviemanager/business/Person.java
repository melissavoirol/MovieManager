package ch.hearc.ig.odi.moviemanager.business;

import java.util.List;

/**
 *
 * Class business (personne);
 * 
 * @author boris.klett
 */
public class Person {

    private String identifiant;
    private String firstName;
    private String lastName;
    private List movies;

    public Person() {
    }

    public Person(String identifiant, String firstName, String lastName, List movies) {
        this.identifiant = identifiant;
        this.firstName = firstName;
        this.lastName = lastName;
        this.movies = movies;
    }

    public Person(String identifiant, String firstName, String lastName) {
        this.identifiant = identifiant;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
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

    public List getMovies() {
        return movies;
    }

    public void setMovies(List movies) {
        this.movies = movies;
    }

}
