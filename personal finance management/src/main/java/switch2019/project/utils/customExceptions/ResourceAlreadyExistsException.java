package switch2019.project.utils.customExceptions;

public class ResourceAlreadyExistsException extends RuntimeException{

    /**
     * Used when a resource is already inside its collection
     * @param message
     */
    public ResourceAlreadyExistsException (String message) {
        super (message);
    }
}
