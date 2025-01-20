package wind.exception;

/**
 * Represents an exception that is thrown when an illegal or inappropriate argument is passed.
 */
public class IllegalArgumentException extends WindException {

    /**
     * Constructs an IllegalArgumentException with the specified detail message.
     *
     * @param message The detail message.
     */
    public IllegalArgumentException(String message) {
        super(message);
    }
}