package switch2019.project.customExceptions;

public class NoPermissionException extends RuntimeException {

    /**
     * Used when a user has no permission to do a group operation
     * @param message
     */
    public NoPermissionException (String message) {
        super (message);
    }
}
