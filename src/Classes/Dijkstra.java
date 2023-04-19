package Classes;

import java.util.*;
import java.util.stream.Stream;
//algorytm Dikstry stworzyłem z pomocą wiadomosci oraz kodu z kanału Geekific i zmodywikowałem go pod własne potrzeby
public class Dijkstra {
    public static HashMap<Station, Integer> calculatePath(Station source, Station target) {
        source.setDistance(0);
        Set<Station> settledStations = new HashSet<>();
        Queue<Station> unsettledStations = new PriorityQueue<>(Collections.singleton(source));
        while (!unsettledStations.isEmpty()) {
            Station currentStation = unsettledStations.poll();
            if (currentStation == target){
                break;
            }
            currentStation.getConnectedStations().entrySet().stream().
                    filter(entry -> !settledStations.contains(entry.getKey())).forEach(entry -> entry.getKey().setDistance(Integer.MAX_VALUE));
            currentStation.getConnectedStations().entrySet()
                    .stream().filter(entry -> !settledStations.contains(entry.getKey()))
                    .forEach(entry -> {
                        evaluateDistance(entry.getKey(), entry.getValue(), currentStation);
                        unsettledStations.add(entry.getKey());
                    });
            settledStations.add(currentStation);
        }
        settledStations.forEach(entry -> entry.setShortestPath(new LinkedList<>()));
        unsettledStations.forEach(entry -> entry.setShortestPath(new LinkedList<>()));
        HashMap<Station, Integer> path = new LinkedHashMap<>();
        target.getShortestPath().forEach(entry -> {
            path.put(entry, entry.getDistance());
            entry.setShortestPath(new LinkedList<>());
        });
        path.put(target, target.getDistance());
        target.setShortestPath(new LinkedList<>());
        return path;
    }

    private static void evaluateDistance(Station connectedStation, Integer distance, Station source) {
        int newDistance = source.getDistance() + distance;
        if (newDistance < connectedStation.getDistance()) {
            connectedStation.setDistance(newDistance);
            connectedStation.setShortestPath(Stream.concat(source.getShortestPath().stream(), Stream.of(source)).toList());
        }
    }
} 
