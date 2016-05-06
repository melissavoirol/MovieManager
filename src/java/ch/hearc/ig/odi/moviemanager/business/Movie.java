package ch.hearc.ig.odi.moviemanager.business;

import java.io.Serializable;

/**
 *
 * @author Melissa Voirol <melissa.voirol@he-arc.ch>
 */
public class Movie implements Serializable {
    
    private Long id;
    private String name;
    private String producer;
    
    /**
     * Mandatory parameterized constructor of the Movie class
     * @param id Unique number of the movie
     * @param name Name of the movie
     * @param producer Producer of the movie
     */
    public Movie(Long id, String name, String producer) {
        this.id = id;
        this.name = name;
        this.producer = producer;
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
