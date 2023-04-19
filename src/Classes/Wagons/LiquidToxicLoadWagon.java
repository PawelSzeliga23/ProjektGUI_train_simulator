package Classes.Wagons;

import Interfaces.Liquid;

public class LiquidToxicLoadWagon extends HeavyLoadWagon implements Liquid {
    private final double density;
    private final String company;
    private final double degreeOfToxicity;
    private final String application;

    public LiquidToxicLoadWagon(double netWeight, double maximumWeight, String nameOfLoad,
                                double loadCapacity, String protection, double density, String company,
                                double degreeOfToxicity, String application) {
        super(netWeight, maximumWeight, nameOfLoad, loadCapacity, protection);
        this.density = density;
        this.company = company;
        this.degreeOfToxicity = degreeOfToxicity;
        this.application = application;
    }

    @Override
    public String toString() {
        return super.toString("Liquid Toxic") + ", density = " + String.format("%.3f", density) +
                ", degree of toxicity = " + String.format("%.2f", degreeOfToxicity) + ", application = " + application +
                ", company = " + company;
    }

    public void communicate() {
        System.out.println("Very dangerous");
    }
}
