package Exceptions;

public class MaxElectricalConnectionException extends Exception {
    public MaxElectricalConnectionException() {
        super("You can not connect this wagon to locomotive because," +
                " maximum number of electrical connected wagons is reached");
    }
}
