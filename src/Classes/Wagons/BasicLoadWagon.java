package Classes.Wagons;

import Classes.Wagon;
import Exceptions.LoadToHeavyException;

public class BasicLoadWagon extends Wagon {
    private final String nameOfLoad;
    private double loadWeight;
    private final double loadCapacity; //m^3

    public BasicLoadWagon(double netWeight, double maximumWeight, String nameOfLoad,
                          double loadCapacity) {
        super(netWeight, maximumWeight, false);
        this.nameOfLoad = nameOfLoad;
        this.loadCapacity = loadCapacity;
    }

    @Override
    public String toString() {
        return super.getIdentificationNumber() + ". Basic Load Wagon" + super.toString() +
                ", Name of load = " + nameOfLoad + ", load capacity = " + String.format("%.2f", loadCapacity) + "m^3"
                + ", load weight = " + String.format("%.2f", loadWeight) + "kg";
    }

    @Override
    public <Double> void load(Double loadWeight) throws LoadToHeavyException {
        if ((double) loadWeight > super.getMaximumWeight() - getNetWeight()) {
            throw new LoadToHeavyException();
        } else {
            this.loadWeight = (double) loadWeight;
        }
    }

    public String toString(String type) {
        return super.getIdentificationNumber() + ". " + type + " Basic Load Wagon" + super.toString() +
                ", Name of load = " + nameOfLoad + ", load capacity = " + String.format("%.2f", loadCapacity) + "m^3" +
                ", load weight = " + String.format("%.2f", loadWeight) + "kg";
    }

    public String loadInformation() {
        return nameOfLoad + " " + String.format("%.2f", loadWeight);
    }
}
