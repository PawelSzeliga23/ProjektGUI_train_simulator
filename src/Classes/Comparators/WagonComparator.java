package Classes.Comparators;

import Classes.Wagon;

import java.util.Comparator;

public class WagonComparator implements Comparator<Wagon> {
    @Override
    public int compare(Wagon o1, Wagon o2) {
        return Double.compare(o1.getNetWeight(), o2.getNetWeight());
    }
}
