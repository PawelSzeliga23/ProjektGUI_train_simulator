package Classes.Wagons;

public class ToxicLoadWagon extends HeavyLoadWagon {
    private final double degreeOfToxicity;
    private final String application;

    public ToxicLoadWagon(double netWeight, double maximumWeight, String nameOfLoad, double loadCapacity, String protection, double degreeOfToxicity, String application) {
        super(netWeight, maximumWeight, nameOfLoad, loadCapacity, protection);
        this.degreeOfToxicity = degreeOfToxicity;
        this.application = application;
    }

    @Override
    public String toString() {
        return super.toString("Toxic") + ", degree of Toxicity = " + String.format("%.2f", degreeOfToxicity) + ", application = " + application;
    }

    public void communicate() {
        System.out.println("Don't you know that i'm toxic?");
    }
    //parafraza tekstu pisenki Britney Spears - Toxic
}
