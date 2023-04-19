package Exceptions;

public class LoadToHeavyException extends Exception {
    public LoadToHeavyException() {
        super("Load is to heavy, please enter new load weight");
    }
}
