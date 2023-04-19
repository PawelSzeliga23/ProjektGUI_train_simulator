package Classes.Wagons;

public class GasLoadWagon extends BasicLoadWagon {
    private final double pressure;
    private final int numberOfValves;

    public GasLoadWagon(double netWeight, double maximumWeight, String nameOfLoad, double loadCapacity, double pressure, int numberOfValves) {
        super(netWeight, maximumWeight, nameOfLoad, loadCapacity);
        this.pressure = pressure;
        this.numberOfValves = numberOfValves;
    }

    @Override
    public String toString() {
        return super.toString("Gas") +
                "pressure = " + String.format("%.2f", pressure) +
                ", numberOfValves = " + numberOfValves;
    }

    public void underPressure() {
        System.out.println("Pressure pushin' down on me\n" +
                "Pressin' down on you, no man ask for\n" +
                "Under pressure that brings a building down\n" +
                "Splits a family in two, puts people on streets\n" +
                "Mm-ba-ba-beh, mm-ba-ba-beh\n" +
                "Dee-day-da, ee-day-da\n" +
                "Queen - Under Pressure");
    }
    //pierwsza zwrotka piosenki Queen - Under Pressure
}
