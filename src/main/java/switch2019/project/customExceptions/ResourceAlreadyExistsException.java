package switch2019.project.customExceptions;

public class ResourceAlreadyExistsException extends RuntimeException{

    /**
     * Used when a resource is already inside its collection
     * @param message
     */
    public ResourceAlreadyExistsException (String message) {
        super (message);
    }
}
