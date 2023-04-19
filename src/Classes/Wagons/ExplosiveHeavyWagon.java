package Classes.Wagons;

public class ExplosiveHeavyWagon extends HeavyLoadWagon {
    private final int kiloTons;
    private final String application;

    public ExplosiveHeavyWagon(double netWeight, double maximumWeight, String nameOfLoad, double loadCapacity, String protection, int kiloTons, String application) {
        super(netWeight, maximumWeight, nameOfLoad, loadCapacity, protection);
        this.kiloTons = kiloTons;
        this.application = application;
    }

    public String toString() {
        return super.toString("Explosive") + ", TNT equivalent = " + kiloTons + ", application = " + application;
    }

    public void fatMan() {
        System.out.println("Bum!");
    }
    //fatMan jedna z broni z FallOut 4. Bron strzelala mini atomowkami
}
