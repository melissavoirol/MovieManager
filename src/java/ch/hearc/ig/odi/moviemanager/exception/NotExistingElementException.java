package ch.hearc.ig.odi.moviemanager.exception;

/**
 *
 * @author Boris Klett <boris.klett@he-arc.ch>
 */
public class NotExistingElementException extends Exception {

    public NotExistingElementException() {
        super();
    }

    /**
     *
     * @param message
     */
    public NotExistingElementException(String message) {
        super(message);
    }

    /**
     *
     * @param number
     */
    public NotExistingElementException(Long idElement) {
        System.out.println("None element existing with the research id: " + idElement);
    }

    /**
     *
     * @param message
     * @param cause
     */
    public NotExistingElementException(String message, Throwable cause) {
        super(message, cause);
    }

}
