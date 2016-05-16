package ch.hearc.ig.odi.moviemanager.converter;

import ch.hearc.ig.odi.moviemanager.business.Movie;
import ch.hearc.ig.odi.moviemanager.service.Services;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Bean used to convert String as Object / Object as String
 *
 * @author Melissa Voirol <melissa.voirol@he-arc.ch>
 */
@Named(value = "movieLOVConverter")
@RequestScoped
public class MovieLOVConverter implements Converter {

    @Inject
    Services services;

    //Allows to get a movie object (value parameter)
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("getAsOhj " + value);
        if (value == null) {
            return null;
        } else {
            Movie m = services.getMovieById(Long.parseLong(value));
            return m;
        }
    }

    //Defines the String value which will be used as the html value attribute value
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        } else if (value instanceof Movie) {
            return (String.valueOf(((Movie) value).getId()));
        } else {
            return "";
        }
    }

}
