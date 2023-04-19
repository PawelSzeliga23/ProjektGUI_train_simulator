package Exceptions;

public class ToManyPassengersException extends Exception {
    public ToManyPassengersException() {
        super("You are trying to put too many passenger, please enter new number of passengers");
    }
}
