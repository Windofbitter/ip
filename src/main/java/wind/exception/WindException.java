package wind.exception;

/**
 * Represents a custom exception for the Wind application.
 */
public class WindException extends RuntimeException {

    /**
     * Constructs a WindException with the specified detail message.
     *
     * @param message The detail message.
     */
    public WindException(String message) {
        super(message);
    }
}
