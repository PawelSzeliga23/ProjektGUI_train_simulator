package Classes;

import Exceptions.MaxElectricalConnectionException;
import Exceptions.MaxNumberOfWagonException;
import Exceptions.MaxWeightException;
import Exceptions.RailroadHazardException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.Semaphore;
//synchronizacja semphorem została napisana z pomoca dokumentacji java https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Semaphore.html

public class Train extends Thread {
    private static int uniqueNumber;
    private final int identificationNumber;
    private final Locomotive locomotive;
    private final ArrayList<Wagon> Wagons;
    private Semaphore semaphore;

    private HashMap<Station, Integer> path = new LinkedHashMap<>();
    private double currentDistance = 0;
    private int finalDistance;
    private Station currentStation;
    private Station targetStation;
    private int stationCounter = 1;
    private double velocity;
    private double distanceToEnd;

    public Train(Locomotive locomotive) {
        this.identificationNumber = ++uniqueNumber;
        this.locomotive = locomotive;
        Wagons = new ArrayList<>();
        velocity = locomotive.getVelocity();
    }

    public ArrayList<Wagon> getWagons() {
        return Wagons;
    }

    public Locomotive getLocomotive() {
        return locomotive;
    }

    public int getIdentificationNumber() {
        return this.identificationNumber;
    }

    public double getDistanceToEnd() {
        return distanceToEnd;
    }

    public Station getTargetStation() {
        return this.targetStation;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void addWagon(Wagon wagon) throws MaxNumberOfWagonException, MaxWeightException, MaxElectricalConnectionException {
        double weightCount = 0;
        int electricConnectionCounter = 0;
        for (Wagon entry : Wagons) {
            weightCount += entry.getMaximumWeight();
            if (entry.isNeedElectricalConnection())
                electricConnectionCounter++;
        }
        if (wagon.isNeedElectricalConnection()) {
            electricConnectionCounter++;
        }
        if (this.locomotive.getMaxNumberOfWagons() < Wagons.size() + 1) {
            throw new MaxNumberOfWagonException();
        } else if (this.locomotive.getMaxWeightOfCargo() < weightCount + wagon.getMaximumWeight()) {
            throw new MaxWeightException();
        } else if (this.locomotive.getMaxElectricalConnectedWagonsNumber() < electricConnectionCounter) {
            throw new MaxElectricalConnectionException();
        } else {
            Wagons.add(wagon);
        }

    }

    public void setPath(HashMap<Station, Integer> path) {
        this.path = path;
        setCurrentStation(path.keySet().stream().toList().get(0));
        setTargetStation(path.keySet().stream().toList().get(stationCounter));
        finalDistance = path.get(locomotive.getTargetStation());
        path.keySet().stream().toList().get(0).addToQueue(this);
        this.distanceToEnd = finalDistance - currentDistance;
    }

    public void setCurrentStation(Station currentStation) {
        this.currentStation = currentStation;
    }

    public void setTargetStation(Station targetStation) {
        this.targetStation = targetStation;
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public synchronized void increaseCurrentDistance() {
        this.currentDistance += speedRecalculation();
        this.distanceToEnd = finalDistance - currentDistance;
    }

    public synchronized void reachingNextStation() {
        if (Math.round(currentDistance) == finalDistance) {
            System.out.println(this.identificationNumber + ". Train has arrived at end Station :" + locomotive.getTargetStation());
            stationCounter = 1;
            currentDistance = 0;
            locomotive.reverseStation();
            setPath(Dijkstra.calculatePath(locomotive.getSourceStation(), locomotive.getTargetStation()));
            semaphore.release();
            semaphore = null;
            try {
                wait(30000);
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (Math.round(currentDistance) == path.get(targetStation)) {
            System.out.println(this.identificationNumber + " Train, has arrived at " + targetStation + ", next station " + path.keySet().stream().toList().get(++stationCounter));
            currentDistance = Math.round(currentDistance);
            currentStation.removeFromQueue(this);
            targetStation.addToQueue(this);
            currentStation = targetStation;
            setTargetStation(path.keySet().stream().toList().get(stationCounter));
            semaphore.release();
            semaphore = null;
            try {
                wait(2000);
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public synchronized void randomChangeOfVelocity() {
        try {
            railroadHazardChecker();
        } catch (RailroadHazardException e) {
            System.out.println(e);
            this.velocity -= 10;
            return;
        }
        if (Math.random() < 0.5) {
            this.velocity = this.velocity * 0.97;
        } else {
            this.velocity = this.velocity * 1.03;
        }
    }

    private void railroadHazardChecker() throws RailroadHazardException {
        if (this.velocity >= 200) {
            throw new RailroadHazardException(this.exceptionCommunicate());
        }
    }

    public double speedRecalculation() {
        return (velocity / 3600);
    }

    @Override
    public synchronized void run() {
        try {
            wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while (this.getState() != State.WAITING) {
            if (Simulation.simulationIsRunning){
                randomChangeOfVelocity();
                increaseCurrentDistance();
                reachingNextStation();
                try {
                    wait(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                try {
                    wait(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public synchronized String toString() {
        if (path.isEmpty()) {
            return identificationNumber + ". Train, locomotive = " + locomotive.toString() + ", current Velocity = " + String.format("%.2f", velocity) +
                    " km/h , with " + Wagons.size() + " wagons, current station = " + currentStation + ", next station = " + targetStation;
        } else {
            return identificationNumber + ". Train, locomotive = " + locomotive.toString() + ", current Velocity = " + String.format("%.2f", velocity) +
                    " km/h , with " + Wagons.size() + " wagons, current station = " + currentStation + ", next station = " + targetStation + ", path = " + path.entrySet();
        }
    }

    public String shortTrainString() {
        return this.identificationNumber + ". Train with " + Wagons.size() + " wagons in sequence: ";
    }

    public synchronized String AppStateString() {
        return this.identificationNumber + ". Train, distance to go " + String.format("%.2f", distanceToEnd) + " km , current station = " + currentStation + " ,next station = " + targetStation;
    }

    public synchronized String allBasicInformation() {
        if (this.getLocomotive().getSourceStation() == null) {
            return "Train does not have a start or end station, this train has not started";
        } else {
            return this + ", traveled distance = " + String.format("%.3f", currentDistance) + " km " + '\n' +
                    "Traveled distance between starting and end station in percentage = " + String.format("%.2f", (currentDistance / finalDistance) * 100) + "%" + '\n' +
                    wagonsToString() + "Traveled distance between current and target station in percentage = " +
                    String.format("%.2f", (((currentDistance - path.get(currentStation)) / (path.get(targetStation) - path.get(currentStation))) * 100)) + "%";
        }
    }

    private synchronized String wagonsToString() {
        if (Wagons.isEmpty()) {
            return "";
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Wagons:");
            stringBuilder.append('\n');
            Wagons.forEach(entry -> {
                stringBuilder.append(entry.toString());
                stringBuilder.append('\n');
            });
            return stringBuilder.toString();
        }
    }

    private synchronized String exceptionCommunicate() {
        return this.identificationNumber + ". Train , velocity = " + String.format("%.2f", velocity) + " km/h ";
    }
}

