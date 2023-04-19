package Classes.Options;

import Classes.Comparators.TrainComparator;
import Classes.Comparators.WagonComparator;
import Classes.Simulation;
import Classes.Train;
import Classes.Wagon;

import java.io.*;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MyFileWriter extends Thread {
    private final Simulation simulation;
    private HashMap<String, List<Wagon>> trainsWagonList = new LinkedHashMap<>();
    private List<Train> trainQueue = new ArrayList<>();
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd/ HH:mm:ss");

    public MyFileWriter(Simulation simulation) {
        this.simulation = simulation;
    }

    public void setDataInOrder() {
        simulation.getTrainList().forEach(entry ->
                {
                    trainQueue.add(entry);
                    List<Wagon> wagonQueue = new ArrayList<>(entry.getWagons());
                    wagonQueue.sort(new WagonComparator());
                    trainsWagonList.put(entry.shortTrainString(), wagonQueue);
                }
        );
        trainQueue.sort(new TrainComparator().reversed());
    }

    public void deleteDate() {
        this.trainsWagonList = new LinkedHashMap<>();
        this.trainQueue = new ArrayList<>();
    }

    @Override
    public void run() {
        int counter = 0;
        while (this.getState() != State.WAITING) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            setDataInOrder();
            try {
                FileWriter fw = new FileWriter("src/AppState.txt", true);
                PrintWriter writer = new PrintWriter(fw);
                writer.println("==============" + ++counter + ". State " + dtf.format(LocalDateTime.now()) + "==============");
                trainsWagonList.entrySet().forEach(entry -> {
                    writer.print(entry.getKey());
                    entry.getValue().forEach(entryWagon -> writer.print(entryWagon.shortString(entryWagon.getClass().getSimpleName()) + "; "));
                    writer.println();
                });
                trainQueue.forEach(entry -> writer.println(entry.AppStateString()));
                writer.close();
                deleteDate();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}