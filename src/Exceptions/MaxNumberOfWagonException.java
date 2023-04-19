package Exceptions;

public class MaxNumberOfWagonException extends Exception {
    public MaxNumberOfWagonException() {
        super("You can attach this wagon, because locomotive's maximum number of connected wagons is reached");
    }
}
