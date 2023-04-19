package Exceptions;

public class MaxWeightException extends Exception {
    public MaxWeightException() {
        super("This locomotive will be overloaded(weight), You can not connect this wagon");
    }
}
