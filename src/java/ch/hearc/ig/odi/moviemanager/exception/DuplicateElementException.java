package ch.hearc.ig.odi.moviemanager.exception;

/**
 *
 * @author Melissag Voirol <melissa.voirol@he-arc.ch>
 */
public class DuplicateElementException extends Exception {

    public DuplicateElementException() {
        super();
    }

    /**
     *
     * @param message
     */
    public DuplicateElementException(String message) {
        super(message);
    }

    /**
     *
     * @param idElement
     */
    public DuplicateElementException(Long idElement) {
        System.out.println("An element with this id: " + idElement + " already exist.");
    }
    
    /**
     *
     * @param message
     * @param cause
     */
    public DuplicateElementException(String message, Throwable cause) {
        super(message, cause);
    }

}
