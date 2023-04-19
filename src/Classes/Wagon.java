package Classes;

import Exceptions.LoadToHeavyException;
import Exceptions.ToManyPassengersException;
import Exceptions.TooManyLetters;

public abstract class Wagon {
    private final int identificationNumber;
    private static int uniqueNumber;
    private final double netWeight;
    private final double maximumWeight;
    private boolean needElectricalConnection;

    public Wagon(double netWeight, double maximumWeight, boolean needElectricalConnection) {
        this.identificationNumber = ++uniqueNumber;
        this.netWeight = netWeight;
        this.maximumWeight = maximumWeight;
        this.needElectricalConnection = needElectricalConnection;
    }

    @Override
    public synchronized String toString() {
        return ", netWeight = " + String.format("%.2f", netWeight) +
                ", maximumWeight = " + String.format("%.2f", maximumWeight) +
                ", needElectricalConnection = " + needElectricalConnection;
    }

    public int getIdentificationNumber() {
        return identificationNumber;
    }

    public double getMaximumWeight() {
        return maximumWeight;
    }

    public double getNetWeight() {
        return netWeight;
    }

    public boolean isNeedElectricalConnection() {
        return needElectricalConnection;
    }

    public void setNeedElectricalConnection(boolean needElectricalConnection) {
        this.needElectricalConnection = needElectricalConnection;
    }

    public String shortString(String className) {
        return this.identificationNumber + ". " + className + ", weight = " + String.format("%.2f", this.netWeight) + " kg ";
    }

    public abstract <T> void load(T loadWeight) throws LoadToHeavyException, ToManyPassengersException, TooManyLetters;
}
