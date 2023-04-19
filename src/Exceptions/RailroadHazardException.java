package Exceptions;

public class RailroadHazardException extends Exception {
    public RailroadHazardException(String input) {
        super(input + "Train is going to fast, train will be slowed down");
    }
}
