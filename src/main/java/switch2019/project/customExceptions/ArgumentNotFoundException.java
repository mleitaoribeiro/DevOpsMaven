package switch2019.project.customExceptions;

public class ArgumentNotFoundException extends RuntimeException {

    /**
     * Used when a resource is not found inside its collection
     * @param message
     */
    public ArgumentNotFoundException (String message) {
        super (message);
    }
}
