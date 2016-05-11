package ch.hearc.ig.odi.moviemanager.presentation.bean;

import ch.hearc.ig.odi.moviemanager.exception.DuplicateElementException;
import ch.hearc.ig.odi.moviemanager.service.Services;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Bean tied to the createMovie.xhtml page, allows to create a new movie
 *
 * @author Melissa Voirol <melissa.voirol@he-arc.ch>
 */
@Named(value = "movieCreateBean")
@RequestScoped
public class MovieCreateBean implements Serializable {

    @Inject
    Services services;

    private Long id;
    private String name;
    private String producer;

    public MovieCreateBean() {

    }

    /**
     * Allows to create the new movie entered in the form
     *
     * @return "success" if the recording goes well, "errorDuplicate" if the
     * number of the movie is already used
     */
    public String saveMovie() {
        try {
            services.saveMovie(id, name, producer);
            return "success";
        } catch (DuplicateElementException dee) {
            return "errorDuplicate";
        }
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

}
