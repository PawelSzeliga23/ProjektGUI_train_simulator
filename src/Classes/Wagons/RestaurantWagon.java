package Classes.Wagons;

import Classes.Wagon;

public class RestaurantWagon extends Wagon {
    private final int numberOfStuffMembers;
    private final int numberOfTables;

    public RestaurantWagon(double netWeight, double maximumWeight,
                           int numberOfStuffMembers, int numberOfTables) {
        super(netWeight, maximumWeight, true);
        this.numberOfStuffMembers = numberOfStuffMembers;
        this.numberOfTables = numberOfTables;
    }

    @Override
    public String toString() {
        return super.getIdentificationNumber() + ". Restaurant Wagon" + super.toString() +
                ", Maximum number of tables = " + numberOfTables + ", Number of stuff members = " +
                numberOfStuffMembers;
    }

    @Override
    public <Double> void load(Double loadWeight) {
        System.out.println("You cant load this wagon");
    }

    public void ratatouille() {
        System.out.println("Les rêves des amoureux sont comme le bon vin\n" +
                "Ils donnent de la joie ou bien du chagrin\n" +
                "Affaibli par la faim, je suis malheureux\n" +
                "Volant en chemin tout ce que je peux\n" +
                "Car rien n'est gratuit dans la vie\n" +
                "Camille Delmais, Michael Giacchino - Le Festin");
    }
    //pierwsza zwrotka piosenki z filmu Ratatouille (Pixar,Walt Disney Pictures 2007) Le Festin (Camille Delmais, Michael Giacchino)
}
