package Classes.Wagons;

import Interfaces.Liquid;

public class LiquidLoadWagon extends BasicLoadWagon implements Liquid {
    private final double density;
    private final String company;

    public LiquidLoadWagon(double netWeight, double maximumWeight, String nameOfLoad, double loadCapacity, double density, String company) {
        super(netWeight, maximumWeight, nameOfLoad, loadCapacity);
        this.density = density;
        this.company = company;
    }

    public String toString() {
        return super.toString("Liquid") + ", density = " + String.format("%.3f", density) + ", company = " + company;
    }
}
