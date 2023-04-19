package Classes;


public class Locomotive{
    private static int uniqueNumber;
    private final int identificationNumber;
    private final String name;
    private final double mass;
    private Station sourceStation;
    private Station targetStation;
    private final int maxNumberOfWagons;
    private final double maxWeightOfCargo;
    private final int maxElectricalConnectedWagonsNumber;
    private final double velocity;

    public Locomotive(String name, double mass, int maxNumberOfWagons,
                      double maxWeightOfCargo, int maxElectricalConnectedWagonsNumber,
                      double velocity) {
        this.name = name;
        this.mass = mass;
        this.maxNumberOfWagons = maxNumberOfWagons;
        this.maxWeightOfCargo = maxWeightOfCargo;
        this.maxElectricalConnectedWagonsNumber = maxElectricalConnectedWagonsNumber;
        this.identificationNumber = ++uniqueNumber;
        this.velocity = velocity;
    }

    public void setSourceStation(Station sourceStation) {
        this.sourceStation = sourceStation;
    }

    public void setTargetStation(Station targetStation) {
        this.targetStation = targetStation;
    }

    public int getIdentificationNumber() {
        return identificationNumber;
    }

    public int getMaxNumberOfWagons() {
        return maxNumberOfWagons;
    }

    public double getMaxWeightOfCargo() {
        return maxWeightOfCargo;
    }

    public int getMaxElectricalConnectedWagonsNumber() {
        return maxElectricalConnectedWagonsNumber;
    }

    public String toString() {
        return identificationNumber + ". Locomotive, name = " + name + ", weight = " + String.format("%.2f",mass) + " kg , maximum number of Wagons = " +
                maxNumberOfWagons + ", maximum weight of pulled Wagons = " + String.format("%.2f",maxWeightOfCargo) + " kg , maximum number of electrical connected Wagons = " +
                maxElectricalConnectedWagonsNumber + ", from = " + sourceStation + ", to = " + targetStation;
    }

    public Station getSourceStation() {
        return sourceStation;
    }

    public Station getTargetStation() {
        return targetStation;
    }

    public void reverseStation() {
        Station station = sourceStation;
        sourceStation = targetStation;
        targetStation = station;
    }

    public double getVelocity() {
        return velocity;
    }

    public String getName() {
        return name;
    }
}
