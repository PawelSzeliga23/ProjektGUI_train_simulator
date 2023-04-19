package Classes.Wagons;

import Classes.Wagon;
import Exceptions.LoadToHeavyException;
import Exceptions.ToManyPassengersException;

public class LuggagePostalWagon extends Wagon {
    private int numberOfLuggage;
    private int numberOfLetters;
    private final int maxNumberOfLuggage;
    private final int maxNumberOfLetters;
    private final String protection;

    public LuggagePostalWagon(double netWeight, double maximumWeight, int maxNumberOfLuggage,
                              int maxNumberOfLetters, String protection) {
        super(netWeight, maximumWeight, false);
        this.maxNumberOfLuggage = maxNumberOfLuggage;
        this.maxNumberOfLetters = maxNumberOfLetters;
        this.protection = protection;
    }

    @Override
    public String toString() {
        return super.getIdentificationNumber() + ". LuggagePostal Wagon" +
                super.toString() + ", maximum number of luggage = " + this.maxNumberOfLuggage +
                ", maximum number of letters = " + this.maxNumberOfLetters + ", current number of letters = " +
                numberOfLetters + ", current number of luggage" + numberOfLuggage +
                ", protection system description = " + this.protection;
    }

    @Override
    public <Integer> void load(Integer numberOfLetters) throws LoadToHeavyException, ToManyPassengersException {

    }

    public void inPost() {
        System.out.println("Your package is on its way :)");
    }
    //nawizanie do komuniaktow wysylanych na e-mail przez inPost (twoja paczka jest juz w drodze)
}
