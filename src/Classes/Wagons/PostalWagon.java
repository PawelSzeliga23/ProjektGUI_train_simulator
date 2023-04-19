package Classes.Wagons;

import Classes.Wagon;
import Exceptions.TooManyLetters;

public class PostalWagon extends Wagon {
    private final String sender;
    private final int maxNumberOfLetters;
    private int numberOfLetters;

    public PostalWagon(double netWeight, double maximumWeight,
                       String sender, int maxNumberOfLetters) {
        super(netWeight, maximumWeight, true);
        this.sender = sender;
        this.maxNumberOfLetters = maxNumberOfLetters;
    }

    @Override
    public String toString() {
        return super.getIdentificationNumber() + ". Postal Wagon" + super.toString() +
                ", Sender = " + sender + ", Maximum number of letters = " + maxNumberOfLetters
                + ", current number of letters = " + numberOfLetters;
    }

    @Override
    public <Integer> void load(Integer numberOfLetters) throws TooManyLetters {
        if ((int) numberOfLetters > maxNumberOfLetters) {
            throw new TooManyLetters();
        } else {
            this.numberOfLetters = (int) numberOfLetters;
        }
    }

    public void HogwartsOwl() {
        System.out.println("You,re a wizard Harry!");
    }
    //nawiazanie do kwesti wypowiedzianej przez Hagrida w 1 czesci sagi Harry Potter
}
