package Classes.Wagons;

import Classes.Wagon;
import Exceptions.LoadToHeavyException;
import Exceptions.ToManyPassengersException;

public class HeavyLoadWagon extends Wagon {
    private final String nameOfLoad;
    public double loadWeight;
    private final double loadCapacity;
    private final String protection;

    public HeavyLoadWagon(double netWeight, double maximumWeight, String nameOfLoad, double loadCapacity, String protection) {
        super(netWeight, maximumWeight, false);
        this.nameOfLoad = nameOfLoad;
        this.loadCapacity = loadCapacity;
        this.protection = protection;
    }

    public String toString() {
        return super.getIdentificationNumber() + ". Heavy Load Wagon" + super.toString() +
                ", Name of load = " + nameOfLoad + ", load capacity = " + String.format("%.2f", loadCapacity) + "m^3" +
                ", load weight = " + String.format("%.2f", loadWeight) + "kg" +
                ", protection = " + protection;
    }

    public <Double> void load(Double loadWeight) throws LoadToHeavyException, ToManyPassengersException {
        if ((double) loadWeight > super.getMaximumWeight() - getNetWeight()) {
            throw new LoadToHeavyException();
        } else {
            this.loadWeight = (double) loadWeight;
        }
    }

    public String toString(String type) {
        return super.getIdentificationNumber() + ". " + type + " Heavy Load Wagon" + super.toString() +
                ", Name of load = " + nameOfLoad + ", load capacity = " + String.format("%.2f", loadCapacity) + "m^3" +
                ", load weight = " + String.format("%.2f", loadWeight) + "kg" +
                ", protection = " + protection;
    }

    public void heavyMetal() {
        System.out.println("Ay, ay, ay, ay, ay, ay, ay\n" +
                "Crazy, but that's how it goes\n" +
                "Millions of people living as foes\n" +
                "Maybe it's not too late\n" +
                "To learn how to love\n" +
                "And forget how to hate\n" +
                "Mental wounds not healing\n" +
                "Life's a bitter shame\n" +
                "I'm going off the rails on a crazy train\n" +
                "I'm going off the rails on a crazy train\n" +
                "Ozzy Osbourne - Crazy Train");
    }
    //pierwsza zwrotka i refren piosenki Ozzy'ego Osbourne'a Crazy Train fajnie nawizujaca do tematyki projektu
}
