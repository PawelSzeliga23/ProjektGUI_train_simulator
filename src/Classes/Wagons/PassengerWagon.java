package Classes.Wagons;

import Classes.Wagon;
import Exceptions.LoadToHeavyException;
import Exceptions.ToManyPassengersException;

public class PassengerWagon extends Wagon {
    private final int numberOfSeats;
    private int numberOfPassengers;
    private final boolean isCompartment;

    public PassengerWagon(double netWeight, double maximumWeight,
                          int numberOfSeats, boolean isCompartment) {
        super(netWeight, maximumWeight, true);
        this.numberOfSeats = numberOfSeats;
        this.isCompartment = isCompartment;
    }

    @Override
    public String toString() {
        if (isCompartment) {
            return super.getIdentificationNumber() +
                    ". Compartment Passenger Wagon " + super.toString() + ", Maximum number of seats = " +
                    numberOfSeats + ", current number of passengers = " + numberOfPassengers;
        }
        return super.getIdentificationNumber() +
                ". Passenger Wagon " + super.toString() + ", Maximum number of seats = " +
                numberOfSeats + ", current number of passengers = " + numberOfPassengers;
    }

    @Override
    public <Integer> void load(Integer numberOfPassengers) throws LoadToHeavyException, ToManyPassengersException {
        if ((int) numberOfPassengers > numberOfSeats) {
            throw new ToManyPassengersException();
        } else {
            this.numberOfPassengers = (int) numberOfPassengers;
        }
    }

    public void communicate() {
        System.out.println("TU UU, TU UU!");
    }

    public void whistle() {
        System.out.println("Fi-uu, Fi-uu!");
    }
}
