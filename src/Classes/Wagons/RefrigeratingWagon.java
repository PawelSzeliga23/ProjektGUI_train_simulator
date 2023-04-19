package Classes.Wagons;

public class RefrigeratingWagon extends BasicLoadWagon {
    private final double temperature;
    private final double energyConsumption;

    public RefrigeratingWagon(double netWeight, double maximumWeight, String nameOfLoad, double loadCapacity, double temperature, double energyConsumption) {
        super(netWeight, maximumWeight, nameOfLoad, loadCapacity);
        this.temperature = temperature;
        this.energyConsumption = energyConsumption;
        super.setNeedElectricalConnection(true);
    }

    public String toString() {
        return super.toString("Refrigerating") + ", temperature = " + String.format("%.2f", temperature) + "[C] , energyConsumption = " + String.format("%.2f", energyConsumption) + "kW";
    }

    public void communicate() {
        System.out.println("I,m freezing");
    }
}
