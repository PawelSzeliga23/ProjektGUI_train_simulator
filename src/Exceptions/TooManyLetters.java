package Exceptions;

public class TooManyLetters extends Exception {
    public TooManyLetters() {
        super("You are trying to add too many letters, please enter new number of letters");
    }
}
