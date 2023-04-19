package Classes;

import Classes.Wagons.*;
import Exceptions.MaxElectricalConnectionException;
import Exceptions.MaxNumberOfWagonException;
import Exceptions.MaxWeightException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RandomSimulation {
    private final ArrayList<String> stationsName = new ArrayList<>();
    private final ArrayList<String> loadName = new ArrayList<>();
    private final ArrayList<String> gasLoad = new ArrayList<>();
    private final ArrayList<String> liquidLoad = new ArrayList<>();
    private final ArrayList<String> toxicLoad = new ArrayList<>();
    private final ArrayList<String> explosiveLoad = new ArrayList<>();
    private final ArrayList<String> toxicLiquidLoad = new ArrayList<>();
    private final ArrayList<String> refrigeratedLoad = new ArrayList<>();
    private final ArrayList<String> companiesNames = new ArrayList<>();
    private final ArrayList<String> sendersNames = new ArrayList<>();
    private final ArrayList<String> locomotivesNames = new ArrayList<>();
    private final ArrayList<String> protections = new ArrayList<>();

    public void prepareAssets() {
        try {
            System.out.println("====Preparing assets====");
            Scanner stationScanner = new Scanner(new File("src/TextFiles/stationsNames.txt"));
            while (stationScanner.hasNextLine()) {
                this.stationsName.add(stationScanner.nextLine());
            }
            Scanner LiquidScanner = new Scanner(new File("src/TextFiles/liquidLoad.txt"));
            while (LiquidScanner.hasNextLine()) {
                this.liquidLoad.add(LiquidScanner.nextLine());
            }
            Scanner loadScanner = new Scanner(new File("src/TextFiles/LoadNames.txt"));
            while (loadScanner.hasNextLine()) {
                this.loadName.add(loadScanner.nextLine());
            }
            Scanner gasScanner = new Scanner(new File("src/TextFiles/gasLoad.txt"));
            while (gasScanner.hasNextLine()) {
                this.gasLoad.add(gasScanner.nextLine());
            }
            Scanner toxicScanner = new Scanner(new File("src/TextFiles/toxicLoad.txt"));
            while (toxicScanner.hasNextLine()) {
                this.toxicLoad.add(toxicScanner.nextLine());
            }
            Scanner explosiveScanner = new Scanner(new File("src/TextFiles/explosiveLoad.txt"));
            while (explosiveScanner.hasNextLine()) {
                this.explosiveLoad.add(explosiveScanner.nextLine());
            }
            Scanner toxicLiquidScanner = new Scanner(new File("src/TextFiles/toxicLiquidLoad.txt"));
            while (toxicLiquidScanner.hasNextLine()) {
                this.toxicLiquidLoad.add(toxicLiquidScanner.nextLine());
            }
            Scanner refrigeratedScanner = new Scanner(new File("src/TextFiles/refrigeratedLoad.txt"));
            while (refrigeratedScanner.hasNextLine()) {
                this.refrigeratedLoad.add(refrigeratedScanner.nextLine());
            }
            Scanner companiesScanner = new Scanner(new File("src/TextFiles/companyName.txt"));
            while (companiesScanner.hasNextLine()) {
                this.companiesNames.add(companiesScanner.nextLine());
            }
            Scanner sendersScanner = new Scanner(new File("src/TextFiles/senderName.txt"));
            while (sendersScanner.hasNextLine()) {
                this.sendersNames.add(sendersScanner.nextLine());
            }
            Scanner locomotiveScanner = new Scanner(new File("src/TextFiles/locomotivesNames.txt"));
            while (locomotiveScanner.hasNextLine()) {
                this.locomotivesNames.add(locomotiveScanner.nextLine());
            }
            Scanner protectionScanner = new Scanner(new File("src/TextFiles/protections.txt"));
            while (protectionScanner.hasNextLine()) {
                this.protections.add(protectionScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Simulation randomSimulation() {
        Simulation simulation = new Simulation();
        int firstIndex = (int) (Math.random() * stationsName.size());
        simulation.addStation(new Station(stationsName.get(firstIndex)));
        stationsName.remove(firstIndex);
        int counter = 1;
        for (int i = 0; i < 119; i++) {
            int randomIndex = (int) (Math.random() * stationsName.size());
            simulation.addStation(new Station(stationsName.get(randomIndex)));
            stationsName.remove(randomIndex);
            simulation.addConnection(counter, i + 2, (int) (Math.random() * 500 + 100));
            if (Math.random() < 0.825) {
                counter++;
            }
        }
        for (int i = 0; i < 30; i++) {
            simulation.addTrain(new Train(randomLocomotive()));
        }
        for (int i = 0; i < 250; i++) {
            simulation.addWagon(randomWagon((int) (Math.random() * 12)));
        }
        connectWagonsAndLocomotive(simulation);
        connectTrainsAndStations(simulation);
        return simulation;
    }

    private Locomotive randomLocomotive() {
        return new Locomotive(locomotivesNames.get((int) (Math.random() * locomotivesNames.size())),
                (Math.random() * 130000 + 70000), (int) (Math.random() * 5 + 5), (Math.random() * 2000000 + 2000000),
                (int) (Math.random() * 10), (Math.random() * 60 + 60));
    }

    private Wagon randomWagon(int randomInput) {
        switch (randomInput) {
            case 1 -> {
                return new PassengerWagon((Math.random() * 20000 + 30000), (Math.random() * 5000 + 55000), (int) (Math.random() * 40 + 40), Math.random() < 0.5);
            }
            case 2 -> {
                return new RestaurantWagon((Math.random() * 20000 + 30000), (Math.random() * 5000 + 55000), (int) (Math.random() * 2 + 4), (int) (Math.random() * 10 + 10));
            }
            case 3 -> {
                return new PostalWagon((Math.random() * 20000 + 30000), (Math.random() * 5000 + 55000), sendersNames.get((int) (Math.random() * sendersNames.size())), (int) (Math.random() * 400 + 1000));
            }
            case 4 -> {
                return new LuggagePostalWagon((Math.random() * 20000 + 30000), (Math.random() * 5000 + 55000), (int) (Math.random() * 60 + 100), (int) (Math.random() * 400 + 1000), protections.get((int) (Math.random() * protections.size())));
            }
            case 5 -> {
                return new BasicLoadWagon((Math.random() * 15000 + 20000), (Math.random() * 10000 + 40000), loadName.get((int) (Math.random() * loadName.size())), (Math.random() * 20 + 60));
            }
            case 6 -> {
                return new HeavyLoadWagon(2 * (Math.random() * 15000 + 20000), 2 * (Math.random() * 10000 + 40000), loadName.get((int) (Math.random() * loadName.size())), 1.4 * (Math.random() * 20 + 60), protections.get((int) (Math.random() * protections.size())));
            }
            case 7 -> {
                return new LiquidLoadWagon((Math.random() * 15000 + 20000), (Math.random() * 10000 + 40000), liquidLoad.get((int) (Math.random() * liquidLoad.size())), (Math.random() * 20 + 60), (Math.random() * 10000), companiesNames.get((int) (Math.random() * companiesNames.size())));
            }
            case 8 -> {
                return new GasLoadWagon((Math.random() * 15000 + 20000), (Math.random() * 10000 + 40000), gasLoad.get((int) (Math.random() * gasLoad.size())), (Math.random() * 20 + 60), (Math.random() * 5), (int) (Math.random() * 5));
            }
            case 9 -> {
                return new RefrigeratingWagon((Math.random() * 15000 + 20000), (Math.random() * 10000 + 40000), refrigeratedLoad.get((int) (Math.random() * refrigeratedLoad.size())), (Math.random() * 20 + 60), (Math.random() * 20 - 40), (Math.random() * 20 + 5));
            }
            case 10 -> {
                return new ExplosiveHeavyWagon(2 * (Math.random() * 15000 + 20000), 2 * (Math.random() * 10000 + 40000), explosiveLoad.get((int) (Math.random() * explosiveLoad.size())), 1.4 * (Math.random() * 20 + 60),
                        protections.get((int) (Math.random() * protections.size())), (int) (Math.random() * 80), "unknown");
            }
            case 11 -> {
                return new ToxicLoadWagon(2 * (Math.random() * 15000 + 20000), 2 * (Math.random() * 10000 + 40000), toxicLoad.get((int) (Math.random() * toxicLoad.size())), 1.4 * (Math.random() * 20 + 60),
                        protections.get((int) (Math.random() * protections.size())), (Math.random() * 20), "unknown");
            }
            case 0 -> {
                return new LiquidToxicLoadWagon(2 * (Math.random() * 15000 + 20000), 2 * (Math.random() * 10000 + 40000),
                        toxicLiquidLoad.get((int) (Math.random() * toxicLiquidLoad.size())),
                        1.4 * (Math.random() * 20 + 60), protections.get((int) (Math.random() * protections.size())), (Math.random() * 10000),
                        companiesNames.get((int) (Math.random() * companiesNames.size())), (Math.random() * 20), "unknown");
            }
        }
        return null;
    }

    private void connectWagonsAndLocomotive(Simulation simulation) {
        for (Train train : simulation.getTrainList()) {
            for (int i = 0; i < 10; ) {
                int randomIndex = (int) (Math.random() * simulation.getWagonList().size());
                try {
                    train.addWagon(simulation.getWagonList().get(randomIndex));
                    simulation.getWagonList().remove(simulation.getWagonList().get(randomIndex));
                } catch (MaxNumberOfWagonException | MaxWeightException | MaxElectricalConnectionException e) {
                    i++;
                }
            }
        }
    }
    private void connectTrainsAndStations(Simulation simulation){
        for (int i = 0; i < simulation.getTrainList().size();) {
            int randomIndex1 = (int) (Math.random() * simulation.getStationList().size());
            int randomIndex2 = (int) (Math.random() * simulation.getStationList().size());
            if (randomIndex1 != randomIndex2) {
                simulation.getTrainList().get(i).getLocomotive().setSourceStation(simulation.getStationList().get(randomIndex1));
                simulation.getTrainList().get(i).getLocomotive().setTargetStation(simulation.getStationList().get(randomIndex2));
                i++;
            }
        }
        simulation.calculatePaths();
    }
}
