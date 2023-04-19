package Classes.Options;

import Classes.Simulation;

import static Classes.Options.Inputs.integerCreatorInput;

public class FunctionsPage {
    public static void functionsPage(Simulation simulation , int menuType) {
        functionsPageGraphics();
        functionsPageFunction(Inputs.userMenuInput(), simulation, menuType);
    }

    public static void functionsPageGraphics() {
        System.out.println("------------------");
        System.out.println("    Functions");
        System.out.println("------------------" + '\n');
        System.out.println("Choose one option:");
        System.out.println("1. Attach Wagons to Locomotive");
        System.out.println("2. Set starting and ending Stations to Locomotive");
        System.out.println("3. Load Wagons");
        System.out.println("4. Remove Station");
        System.out.println("5. Remove Train");
        System.out.println("6. Remove Free Locomotive");
        System.out.println("7. Remove Free Wagon");
        System.out.println("8. Back");
    }

    public static void functionsPageFunction(int input, Simulation simulation, int menuType) {
        switch (input) {
            case 1 -> {
                attachWagonsToLocomotive(simulation);
                functionsPage(simulation,menuType);
            }
            case 2 -> {
                setTargetAndSourceStation(simulation);
                functionsPage(simulation,menuType);
            }
            case 3 -> {
                loadWagon(simulation);
                functionsPage(simulation,menuType);
            }
            case 4 -> {
                removeStation(simulation);
                functionsPage(simulation,menuType);
            }
            case 5 -> {
                removeTrain(simulation);
                functionsPage(simulation,menuType);
            }
            case 6 -> {
                removeFreeLocomotive(simulation);
                functionsPage(simulation,menuType);
            }
            case 7 -> {
                removeWagon(simulation);
                functionsPage(simulation, menuType);
            }
            case 8 ->{
                switch (menuType){
                    case 1 -> StartingPage.startingPage(simulation);
                    case 2 -> StartingPage.startingPageWithoutRandom(simulation);
                    case 3 -> MainPage.mainPage(simulation);
                }
            }
            default -> {
                System.out.println(Inputs.incorrectInputCommunicate);
                FunctionsPage.functionsPage(simulation,menuType);
            }
        }
    }

    public static void attachWagonsToLocomotive(Simulation simulation) {
        simulation.showLocomotives();
        System.out.print("Please select Locomotive:");
        int input1 = integerCreatorInput();
        simulation.showWagons();
        System.out.print("Please select wagons:");
        int input2 = integerCreatorInput();
        simulation.addWagon(input1, input2);
    }

    public static void setTargetAndSourceStation(Simulation simulation) {
        simulation.showLocomotives();
        System.out.print("Please select Locomotive:");
        int inputLocomotiveID = integerCreatorInput();
        simulation.showStations();
        System.out.print("Please select beginning station:");
        int inputStationBeginID = integerCreatorInput();
        simulation.showStations();
        System.out.print("Please select target station:");
        int inputStationTargetID = integerCreatorInput();
        simulation.setSourceAndTargetStation(inputLocomotiveID, inputStationBeginID, inputStationTargetID);
    }

    public static void removeStation(Simulation simulation) {
        simulation.showStations();
        System.out.print("Please select Station that you want to remove:");
        int stationId = integerCreatorInput();
        simulation.removeStation(stationId);
    }

    public static void removeTrain(Simulation simulation) {
        simulation.showTrains();
        System.out.print("Please select Train that you wont to remove:");
        int trainId = integerCreatorInput();
        simulation.removeTrain(trainId);
    }

    public static void removeFreeLocomotive(Simulation simulation) {
        simulation.showFreeLocomotives();
        System.out.print("Please select Locomotive that you want to remove:");
        int locomotiveID = integerCreatorInput();
        simulation.removeLocomotive(locomotiveID);
    }

    public static void removeWagon(Simulation simulation) {
        simulation.showWagons();
        System.out.print("Please select Wagon that you want to remove:");
        int wagonId = integerCreatorInput();
        simulation.removeWagon(wagonId);
    }
    public static void loadWagon(Simulation simulation){
        simulation.showWagons();
        System.out.println("Please select which wagon you wont to load");
        int wagonID = integerCreatorInput();
        simulation.loadWagon(wagonID);
    }
}
