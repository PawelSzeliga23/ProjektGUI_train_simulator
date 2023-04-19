package Classes.Comparators;

import Classes.Train;

import java.util.Comparator;

public class TrainComparator implements Comparator<Train> {
    @Override
    public int compare(Train o1, Train o2) {
        return Double.compare(o1.getDistanceToEnd(), o2.getDistanceToEnd());
    }
}
