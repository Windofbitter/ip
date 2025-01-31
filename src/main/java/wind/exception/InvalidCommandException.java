package wind.exception;

/**
 * Represents an exception that is thrown when an invalid command is encountered.
 */
public class InvalidCommandException extends WindException {

    /**
     * Constructs an InvalidCommandException with the specified detail message.
     *
     * @param message The detail message.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
