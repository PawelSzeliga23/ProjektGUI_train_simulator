package Classes;

import java.util.*;
import java.util.concurrent.Semaphore;

public class Station extends Thread implements Comparable<Station> {
    private static int uniqueNumber;
    private final int identificationNumber;
    private final String name;
    private Integer distance;

    private List<Station> shortestPath = new LinkedList<>();
    private final Map<Station, Integer> connectedStations = new HashMap<>();

    private final HashMap<Station, Semaphore> pathBlocker = new LinkedHashMap<>();

    private final List<Train> trainQueue = new LinkedList<>();

    public Station(String name) {
        this.name = name;
        this.identificationNumber = ++uniqueNumber;
    }

    public void addConnectedStation(Station station, int distance) {
        connectedStations.put(station, distance);
        station.addConnection(this, distance);
    }

    private void addConnection(Station station, int distance) {
        connectedStations.put(station, distance);
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public List<Station> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Station> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Map<Station, Integer> getConnectedStations() {
        return connectedStations;
    }

    public String toString() {
        return this.identificationNumber + ". " + this.name;
    }

    public int getIdentificationNumber() {
        return identificationNumber;
    }

    @Override
    public int compareTo(Station o) {
        return Integer.compare(this.distance, o.getDistance());
    }

    public void removeConnection() {
        connectedStations.entrySet().forEach(entry -> entry.getKey().getConnectedStations().remove(this));
    }

    public synchronized void addToQueue(Train train) {
        trainQueue.add(train);
    }

    public synchronized void removeFromQueue(Train train) {
        trainQueue.remove(train);
    }

    private void pathBlockerBuilder() {
        connectedStations.forEach((key, value) -> pathBlocker.put(key, new Semaphore(1)));
    }

    public synchronized void deploy() throws InterruptedException {
        for (Train train : trainQueue) {
            if (train.getState() == State.WAITING && train.getSemaphore() != null) {
                if (train.getSemaphore().tryAcquire()) {
                    synchronized (train) {
                        train.notify();
                    }
                }
            }
        }
    }

    public synchronized void setSemaphoresToTrains() {
        for (Train train : trainQueue) {
            if (train.getSemaphore() == null) {
                train.setSemaphore(pathBlocker.get(train.getTargetStation()));
            }
        }
    }

    @Override
    public synchronized void run() {
        pathBlockerBuilder();
        while (true) {
            if (Simulation.simulationIsRunning) {
                setSemaphoresToTrains();
                try {
                    deploy();
                    wait(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    wait(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}