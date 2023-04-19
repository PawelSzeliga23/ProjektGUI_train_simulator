package Classes;

import Classes.Options.Inputs;
import Classes.Options.MyFileWriter;
import Classes.Wagons.*;
import Exceptions.*;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Wagon> wagonList = new ArrayList<>();
    private final List<Train> trainList = new ArrayList<>();
    private final List<Station> stationList = new ArrayList<>();
    private final MyFileWriter fileWriter;
    private final List<Train> dontStartedTrainList = new ArrayList<>();
    public static boolean simulationIsRunning;

    public Simulation() {
        this.fileWriter = new MyFileWriter(this);
    }

    public List<Wagon> getWagonList() {
        return wagonList;
    }

    public List<Train> getTrainList() {
        return trainList;
    }

    public List<Station> getStationList() {
        return stationList;
    }

    public void addWagon(Wagon wagon) {
        wagonList.add(wagon);
    }


    public void addTrain(Train train) {
        trainList.add(train);
    }

    public void addStation(Station station) {
        stationList.add(station);
    }

    public void showWagons() {
        wagonList.forEach(System.out::println);
    }

    public void showStations() {
        stationList.forEach(System.out::println);
    }

    public void showTrains() {
        trainList.forEach(System.out::println);
    }

    public void showFreeLocomotives() {
        trainList.forEach(entry -> {
            if (entry.getWagons().isEmpty()) {
                System.out.println(entry.getLocomotive());
            }
        });
    }

    public void showLocomotives() {
        trainList.forEach(entry -> System.out.println(entry.getLocomotive()));
    }

    public void addConnection(int source, int target, int distance) {
        boolean foundSource = false;
        boolean foundTarget = false;
        if (!stationList.isEmpty()) {
            if (source != target) {
                for (Station station1 : stationList) {
                    if (station1.getIdentificationNumber() == source) {
                        foundSource = true;
                        for (Station station2 : stationList) {
                            if (station2.getIdentificationNumber() == target) {
                                foundTarget = true;
                                station1.addConnectedStation(station2, distance);
                                System.out.println("Connection was successfully created");
                            }
                        }
                    }
                }
                if (!foundSource) {
                    System.out.println("There is no such source station");
                }
                if (!foundTarget) {
                    System.out.println("There is no such target station");
                }
            } else System.out.println("You can't connect same station");
        } else System.out.println("There is no stations");
    }

    public void addWagon(int locomotiveId, int wagonId) {
        boolean foundLocomotive = false;
        boolean foundWagon = false;
        if (!trainList.isEmpty()) {
            if (!wagonList.isEmpty()) {
                for (Train train : trainList) {
                    if (train.getLocomotive().getIdentificationNumber() == locomotiveId) {
                        foundLocomotive = true;
                        for (Wagon wagon : wagonList) {
                            if (wagon.getIdentificationNumber() == wagonId) {
                                foundWagon = true;
                                try {
                                    train.addWagon(wagon);
                                    System.out.println("You have successfully connected wagon");
                                    wagonList.remove(wagon);
                                } catch (MaxNumberOfWagonException | MaxWeightException |
                                         MaxElectricalConnectionException e) {
                                    System.out.println(e);
                                }
                            }
                        }
                    }
                }
                if (!foundWagon) {
                    System.out.println("there is no such wagon");
                }
                if (!foundLocomotive) {
                    System.out.println("There is no such locomotive");
                }
            } else System.out.println("There is no wagons");
        } else System.out.println("There is no locomotives");
    }

    public void setSourceAndTargetStation(int locomotiveID, int beginStationId, int targetStationId) {
        boolean foundSource = false;
        boolean foundTarget = false;
        boolean fountLocomotive = false;
        if (!stationList.isEmpty()) {
            if (!trainList.isEmpty()) {
                for (Train train : trainList) {
                    if (train.getLocomotive().getIdentificationNumber() == locomotiveID) {
                        if (beginStationId != targetStationId) {
                            for (Station begin : stationList) {
                                if (begin.getIdentificationNumber() == beginStationId) {
                                    foundSource = true;
                                    for (Station target : stationList) {
                                        if (target.getIdentificationNumber() == targetStationId) {
                                            foundTarget = true;
                                            train.getLocomotive().setSourceStation(begin);
                                            train.getLocomotive().setTargetStation(target);
                                            train.setPath(Dijkstra.calculatePath(begin,target));
                                            System.out.println("You have successfully set begin and target Station");
                                        }
                                    }
                                }
                            }
                            if (!fountLocomotive) {
                                System.out.println("There is no such Locomotive");
                            }
                            if (!foundSource) {
                                System.out.println("There is no such beginning station");
                            }
                            if (!foundTarget) {
                                System.out.println("There is no such target station");
                            }
                        } else System.out.println("You can't connect same station");
                    }
                }
            } else System.out.println("There is no Trains");
        } else System.out.println("There is no stations");
    }

    public void removeStation(int stationID) {
        boolean foundStation = false;
        if (!stationList.isEmpty()) {
            for (Station station : stationList) {
                if (station.getIdentificationNumber() == stationID) {
                    foundStation = true;
                    station.removeConnection();
                    stationList.remove(station);
                    System.out.println("Station was removed successfully");
                    break;
                }
            }
            if (!foundStation) {
                System.out.println("There is no such station");
            }
        } else System.out.println("There is no stations");
    }

    public void removeTrain(int trainId) {
        boolean foundTrain = false;
        if (!trainList.isEmpty()) {
            for (Train train : trainList) {
                if (train.getIdentificationNumber() == trainId) {
                    foundTrain = true;
                    if (train.getWagons().isEmpty()) {
                        trainList.remove(train);
                    } else {
                        wagonList.addAll(train.getWagons());
                    }
                    System.out.println("Train was removed successfully");
                    break;
                }
            }
            if (!foundTrain)
                System.out.println("There is no such train");
        } else System.out.println("There is no Trains");
    }

    public void removeLocomotive(int locomotiveID) {
        boolean foundLocomotive = false;
        if (!trainList.isEmpty()) {
            for (Train train : trainList) {
                if (train.getWagons().size() == 0) {
                    if (train.getLocomotive().getIdentificationNumber() == locomotiveID) {
                        foundLocomotive = true;
                        trainList.remove(train);
                        System.out.println("Locomotive was removed successfully");
                        break;
                    }
                }
            }
            if (!foundLocomotive)
                System.out.println("There is no such locomotive");
        } else System.out.println("There is no trains");
    }

    public void removeWagon(int wagonId) {
        boolean foundWagon = false;
        if (!wagonList.isEmpty()) {
            for (Wagon wagon : wagonList) {
                if (wagon.getIdentificationNumber() == wagonId) {
                    foundWagon = true;
                    wagonList.remove(wagon);
                    System.out.println("Wagon was removed successfully");
                    break;
                }
            }
            if (!foundWagon)
                System.out.println("There is no such wagon");
        } else System.out.println("There is no Wagons");
    }

    public void showAllConnections() {
        for (Station station : stationList) {
            System.out.println(station + ", connections(name of station, distance[km]):" + station.getConnectedStations().entrySet());
        }
    }

    public void calculatePaths() {
        trainList.forEach(entry -> entry.setPath(Dijkstra.calculatePath(entry.getLocomotive().getSourceStation(), entry.getLocomotive().getTargetStation())));
    }

    public void startSimulation() {
        simulationIsRunning = true;
        stationList.forEach(Thread::start);
        trainList.forEach(entry -> {
            if (entry.getLocomotive().getSourceStation() == null || entry.getLocomotive().getTargetStation() == null) {
                System.out.println("Train does not have a start or end station, this train has not started");
                dontStartedTrainList.add(entry);
            } else {
                entry.start();
            }
        });
        fileWriter.start();
    }

    public void stopSimulation() {
        simulationIsRunning = false;
    }

    public void resumeSimulation() {
        simulationIsRunning = true;
        dontStartedTrainList.forEach(entry -> {
            if (entry.getLocomotive().getSourceStation() == null || entry.getLocomotive().getTargetStation() == null) {
                System.out.println("Train does not have a start or end station, this train has not started");
            } else {
                entry.start();
            }
        });
    }

    public void loadWagon(int wagonID) {
        boolean foundWagon = false;
        if (!wagonList.isEmpty()) {
            for (Wagon wagon : wagonList) {
                if (wagon.getIdentificationNumber() == wagonID) {
                    foundWagon = true;
                    System.out.println(wagon);
                    if (wagon.getClass() == BasicLoadWagon.class ||
                            wagon.getClass() == RefrigeratingWagon.class ||
                            wagon.getClass() == ExplosiveHeavyWagon.class ||
                            wagon.getClass() == GasLoadWagon.class ||
                            wagon.getClass() == LiquidLoadWagon.class ||
                            wagon.getClass() == LiquidToxicLoadWagon.class ||
                            wagon.getClass() == ToxicLoadWagon.class ||
                            wagon.getClass() == HeavyLoadWagon.class
                    ) {
                        System.out.print("Please enter weight:");
                        double weight = Inputs.doubleCreatorInput();
                        try {
                            wagon.load(weight);
                        } catch (LoadToHeavyException | ToManyPassengersException | TooManyLetters e) {
                            System.out.println(e);
                        }
                    } else if (wagon.getClass() == PassengerWagon.class) {
                        System.out.print("Please enter number of passengers");
                        int numberOfPassengers = Inputs.integerCreatorInput();
                        try {
                            wagon.load(numberOfPassengers);
                        } catch (LoadToHeavyException | ToManyPassengersException | TooManyLetters e) {
                            System.out.println(e);
                        }
                    } else if (wagon.getClass() == LuggagePostalWagon.class ||
                            wagon.getClass() == PostalWagon.class
                    ) {
                        System.out.println("Please enter number of letters");
                        int numberOfLetters = Inputs.integerCreatorInput();
                        try {
                            wagon.load(numberOfLetters);
                        } catch (LoadToHeavyException | ToManyPassengersException | TooManyLetters e) {
                            System.out.println(e);
                        }
                    } else {
                        try {
                            wagon.load(0);
                        } catch (LoadToHeavyException | ToManyPassengersException | TooManyLetters e) {
                            System.out.println(e);
                        }
                    }
                }
            }
            if (!foundWagon)
                System.out.println("There is no such wagon");
        } else System.out.println("There is no Wagons");
    }
}
