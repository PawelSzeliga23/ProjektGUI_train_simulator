package Classes.Options;

import Classes.Locomotive;
import Classes.Simulation;
import Classes.Station;
import Classes.Train;
import Classes.Wagons.*;

import static Classes.Options.Inputs.*;

public class CreatorPage {
    static String[] wagonText = {
            "Passenger Wagon", "Restaurant Wagon", "Postal Wagon", "Luggage-Postal Wagon",
            "Basic Load Wagon", "Heavy Load Wagon", "Liquid Load Wagon", "Refrigerating Wagon",
            "Gas Load Wagon", "Explosive Load Wagon", "Toxic Load Wagon", "Liquid Toxic Load Wagon"
    };

    public static void creatorPage(Simulation simulation , int menuType) {
        creatorPageGraphics();
        creatorPageFunction(Inputs.userMenuInput(), simulation , menuType);
    }

    public static void creatorPageGraphics() {
        System.out.println("------------------");
        System.out.println("     Creator");
        System.out.println("------------------" + '\n');
        System.out.println("Choose one option:");
        System.out.println("1. Create new Station");
        System.out.println("2. Create new Locomotive");
        System.out.println("3. Create new Wagon");
        System.out.println("4. Create new Connection");
        System.out.println("5. Back" + '\n');
    }

    public static void creatorPageFunction(int input, Simulation simulation, int menuType) {
        switch (input) {
            case 1 -> {
                stationCreator(simulation);
                creatorPage(simulation, menuType);
            }
            case 2 -> {
                locomotiveCreator(simulation);
                creatorPage(simulation, menuType);
            }
            case 3 -> createWagonPage(simulation, menuType);
            case 4 -> {
                connectionCreator(simulation);
                creatorPage(simulation, menuType);
            }
            case 5 ->{
                switch (menuType){
                    case 1 -> StartingPage.startingPage(simulation);
                    case 2 -> StartingPage.startingPageWithoutRandom(simulation);
                    case 3 -> MainPage.mainPage(simulation);
                }
            }
            default -> {
                System.out.println(incorrectInputCommunicate);
                creatorPage(simulation , menuType);
            }
        }
    }

    public static void createWagonPage(Simulation simulation, int menuType) {
        createWagonGraphics();
        createWagonFunction(Inputs.userMenuInput(), simulation, menuType);
    }

    public static void createWagonGraphics() {
        System.out.println("----------------------");
        System.out.println("    Wagon Creator");
        System.out.println("----------------------" + '\n');
        System.out.println("Select what type of wagon:");
        int counter = 0;
        for (String e : wagonText) {
            System.out.println((++counter) + ". " + e);
        }
        System.out.println(++counter + ". Back");
    }

    public static void createWagonFunction(int input, Simulation simulation , int menuType) {
        switch (input) {
            case 1 -> {
                passengerWagonCreator(simulation);
                createWagonPage(simulation, menuType);
            }
            case 2 -> {
                restaurantWagonCreator(simulation);
                createWagonPage(simulation, menuType);
            }
            case 3 -> {
                postalWagonCreator(simulation);
                createWagonPage(simulation, menuType);
            }
            case 4 -> {
                luggagePostalWagonCreator(simulation);
                createWagonPage(simulation, menuType);
            }
            case 5 -> {
                basicLoadWagonCreator(simulation);
                createWagonPage(simulation, menuType);
            }
            case 6 -> {
                heavyLoadWagonCreator(simulation);
                createWagonPage(simulation, menuType);
            }
            case 7 -> {
                liquidLoadWagonCreator(simulation);
                createWagonPage(simulation, menuType);
            }
            case 8 -> {
                RefrigeratingLoadWagonCreator(simulation);
                createWagonPage(simulation, menuType);
            }
            case 9 -> {
                GasLoadWagonCreator(simulation);
                createWagonPage(simulation, menuType);
            }
            case 10 -> {
                ExplosiveLoadWagonCreator(simulation);
                createWagonPage(simulation, menuType);
            }
            case 11 -> {
                ToxicLoadWagonCreator(simulation);
                createWagonPage(simulation, menuType);
            }
            case 12 -> {
                LiquidToxicLoadWagonCreator(simulation);
                createWagonPage(simulation, menuType);
            }
            case 13 -> creatorPage(simulation , menuType);
            default -> {
                System.out.println(incorrectInputCommunicate);
                createWagonPage(simulation, menuType);
            }
        }
    }

    public static void stationCreator(Simulation simulation) {
        System.out.print("Please enter name of station:");
        String name = scanner.nextLine();
        simulation.addStation(new Station(name));
        System.out.println("The station was successfully created");
    }

    public static void connectionCreator(Simulation simulation) {
        simulation.showStations();
        System.out.print("Please select source station:");
        int input1 = integerCreatorInput();
        simulation.showStations();
        System.out.print("Please select target station:");
        int input2 = integerCreatorInput();
        System.out.print("Please enter distance between them:");
        int distance = integerCreatorInput();
        simulation.addConnection(input1, input2, distance);
    }

    public static void locomotiveCreator(Simulation simulation) {
        System.out.print("Please enter name of locomotive:");
        String name = scanner.nextLine();
        System.out.print("Please enter locomotive's weight:");
        double mass = doubleCreatorInput();
        System.out.print("Please enter maximum number of wagons:");
        int maxWagons = integerCreatorInput();
        System.out.print("Please enter maximum weigh that locomotive can move:");
        double maxCargoWeight = doubleCreatorInput();
        System.out.print("Please enter maximum number of electrical wagons:");
        int maxWagonsElectric = integerCreatorInput();
        System.out.print("Please enter velocity[km/h]:");
        double velocity = doubleCreatorInput();
        Locomotive locomotive = new Locomotive(name, mass, maxWagons, maxCargoWeight, maxWagonsElectric, velocity);
        simulation.addTrain(new Train(locomotive));
        System.out.println("The locomotive was successfully created");
    }

    private static void passengerWagonCreator(Simulation simulation) {
        System.out.print("Please enter wagon's net weight:");
        double netWeight = Inputs.doubleCreatorInput();
        System.out.print("Please enter wagon's max weight:");
        double maxWeight = Inputs.doubleCreatorInput();
        System.out.print("Please enter number of seats (integer number):");
        int numberOfSeats = Inputs.integerCreatorInput();
        System.out.print("Is it compartment (Yes/No):");
        boolean isCompartment = Inputs.booleanCreatorInput();
        simulation.addWagon(new PassengerWagon(netWeight, maxWeight, numberOfSeats, isCompartment));
        System.out.println("The wagon was successfully created");
    }

    private static void restaurantWagonCreator(Simulation simulation) {
        System.out.print("Please enter wagon's net weight:");
        double netWeight = Inputs.doubleCreatorInput();
        System.out.print("Please enter wagon's max weight:");
        double maxWeight = Inputs.doubleCreatorInput();
        System.out.print("Please enter number of stuff members (integer number):");
        int numberOfStuffMembers = Inputs.integerCreatorInput();
        System.out.print("Please enter number of tables (integer number):");
        int numberOfTables = Inputs.integerCreatorInput();
        simulation.addWagon(new RestaurantWagon(netWeight, maxWeight, numberOfStuffMembers, numberOfTables));
        System.out.println("The wagon was successfully created");
    }

    private static void postalWagonCreator(Simulation simulation) {
        System.out.print("Please enter wagon's net weight:");
        double netWeight = Inputs.doubleCreatorInput();
        System.out.print("Please enter wagon's max weight:");
        double maxWeight = Inputs.doubleCreatorInput();
        System.out.print("Please enter maximum number of letters (integer number):");
        int numberOfLetters = Inputs.integerCreatorInput();
        System.out.print("Please enter sender's name:");
        String sender = scanner.nextLine();
        simulation.addWagon(new PostalWagon(netWeight, maxWeight, sender, numberOfLetters));
        System.out.println("The wagon was successfully created");
    }

    private static void luggagePostalWagonCreator(Simulation simulation) {
        System.out.print("Please enter wagon's net weight:");
        double netWeight = Inputs.doubleCreatorInput();
        System.out.print("Please enter wagon's max weight:");
        double maxWeight = Inputs.doubleCreatorInput();
        System.out.print("Please enter maximum number of letters (integer number):");
        int numberOfLetters = Inputs.integerCreatorInput();
        System.out.print("Please enter maximum number of luggage (integer number):");
        int numberOfLuggage = Inputs.integerCreatorInput();
        System.out.println("Please enter description of protection system:");
        String protection = scanner.nextLine();
        simulation.addWagon(new LuggagePostalWagon(netWeight, maxWeight, numberOfLuggage, numberOfLetters, protection));
        System.out.println("The wagon was successfully created");
    }

    private static void basicLoadWagonCreator(Simulation simulation) {
        System.out.print("Please enter wagon's net weight:");
        double netWeight = Inputs.doubleCreatorInput();
        System.out.print("Please enter wagon's max weight:");
        double maxWeight = Inputs.doubleCreatorInput();
        System.out.print("Please enter name of load:");
        String nameOfLoad = scanner.nextLine();
        System.out.print("Please enter load capacity:");
        double loadCapacity = Inputs.doubleCreatorInput();
        simulation.addWagon(new BasicLoadWagon(netWeight, maxWeight, nameOfLoad, loadCapacity));
        System.out.println("The wagon was successfully created");
    }

    private static void heavyLoadWagonCreator(Simulation simulation) {
        System.out.print("Please enter wagon's net weight:");
        double netWeight = Inputs.doubleCreatorInput();
        System.out.print("Please enter wagon's max weight:");
        double maxWeight = Inputs.doubleCreatorInput();
        System.out.print("Please enter name of load:");
        String nameOfLoad = scanner.nextLine();
        System.out.print("Please enter load capacity:");
        double loadCapacity = Inputs.doubleCreatorInput();
        System.out.print("Please enter a brief description of the security features:");
        String protection = scanner.nextLine();
        simulation.addWagon(new HeavyLoadWagon(netWeight, maxWeight, nameOfLoad, loadCapacity, protection));
        System.out.println("The wagon was successfully created");
    }

    private static void liquidLoadWagonCreator(Simulation simulation) {
        System.out.print("Please enter wagon's net weight:");
        double netWeight = Inputs.doubleCreatorInput();
        System.out.print("Please enter wagon's max weight:");
        double maxWeight = Inputs.doubleCreatorInput();
        System.out.print("Please enter name of load:");
        String nameOfLoad = scanner.nextLine();
        System.out.print("Please enter load capacity:");
        double loadCapacity = Inputs.doubleCreatorInput();
        System.out.print("Please enter density of liquid:");
        double density = Inputs.doubleCreatorInput();
        System.out.print("Please enter name of company/sender:");
        String company = scanner.nextLine();
        simulation.addWagon(new LiquidLoadWagon(netWeight, maxWeight, nameOfLoad, loadCapacity, density, company));
        System.out.println("The wagon was successfully created");
    }

    private static void RefrigeratingLoadWagonCreator(Simulation simulation) {
        System.out.print("Please enter wagon's net weight:");
        double netWeight = Inputs.doubleCreatorInput();
        System.out.print("Please enter wagon's max weight:");
        double maxWeight = Inputs.doubleCreatorInput();
        System.out.print("Please enter name of load:");
        String nameOfLoad = scanner.nextLine();
        System.out.print("Please enter load capacity:");
        double loadCapacity = Inputs.doubleCreatorInput();
        System.out.print("Please enter temperature[K]:");
        double temperature = Inputs.doubleCreatorInput();
        System.out.print("Please enter energy consumption:");
        double energyConsumption = Inputs.doubleCreatorInput();
        simulation.addWagon(new RefrigeratingWagon(netWeight, maxWeight, nameOfLoad, loadCapacity, temperature, energyConsumption));
        System.out.println("The wagon was successfully created");
    }

    private static void GasLoadWagonCreator(Simulation simulation) {
        System.out.print("Please enter wagon's net weight:");
        double netWeight = Inputs.doubleCreatorInput();
        System.out.print("Please enter wagon's max weight:");
        double maxWeight = Inputs.doubleCreatorInput();
        System.out.print("Please enter name of load:");
        String nameOfLoad = scanner.nextLine();
        System.out.print("Please enter load capacity:");
        double loadCapacity = Inputs.doubleCreatorInput();
        System.out.print("Please enter pressure:");
        double pressure = Inputs.doubleCreatorInput();
        System.out.print("Please enter number of valves:");
        int numberOfValves = Inputs.integerCreatorInput();
        simulation.addWagon(new GasLoadWagon(netWeight, maxWeight, nameOfLoad, loadCapacity, pressure, numberOfValves));
        System.out.println("The wagon was successfully created");
    }

    private static void ExplosiveLoadWagonCreator(Simulation simulation) {
        System.out.print("Please enter wagon's net weight:");
        double netWeight = Inputs.doubleCreatorInput();
        System.out.print("Please enter wagon's max weight:");
        double maxWeight = Inputs.doubleCreatorInput();
        System.out.print("Please enter name of load:");
        String nameOfLoad = scanner.nextLine();
        System.out.print("Please enter load capacity:");
        double loadCapacity = Inputs.doubleCreatorInput();
        System.out.print("Please enter a brief description of the security features:");
        String protection = scanner.nextLine();
        System.out.print("Please enter power of load[kT]:");
        int kiloTons = Inputs.integerCreatorInput();
        System.out.print("Please enter a brief description of application:");
        String application = scanner.nextLine();
        simulation.addWagon(new ExplosiveHeavyWagon(netWeight, maxWeight, nameOfLoad, loadCapacity, protection, kiloTons, application));
        System.out.println("The wagon was successfully created");
    }

    private static void ToxicLoadWagonCreator(Simulation simulation) {
        System.out.print("Please enter wagon's net weight:");
        double netWeight = Inputs.doubleCreatorInput();
        System.out.print("Please enter wagon's max weight:");
        double maxWeight = Inputs.doubleCreatorInput();
        System.out.print("Please enter name of load:");
        String nameOfLoad = scanner.nextLine();
        System.out.print("Please enter load capacity:");
        double loadCapacity = Inputs.doubleCreatorInput();
        System.out.print("Please enter a brief description of the security features:");
        String protection = scanner.nextLine();
        System.out.print("Please enter degree of toxicity:");
        double degreeOfToxicity = Inputs.doubleCreatorInput();
        System.out.print("Please enter a brief description of application:");
        String application = scanner.nextLine();
        simulation.addWagon(new ToxicLoadWagon(netWeight, maxWeight, nameOfLoad, loadCapacity, protection, degreeOfToxicity, application));
        System.out.println("The wagon was successfully created");
    }

    private static void LiquidToxicLoadWagonCreator(Simulation simulation) {
        System.out.print("Please enter wagon's net weight:");
        double netWeight = Inputs.doubleCreatorInput();
        System.out.print("Please enter wagon's max weight:");
        double maxWeight = Inputs.doubleCreatorInput();
        System.out.print("Please enter name of load:");
        String nameOfLoad = scanner.nextLine();
        System.out.print("Please enter load capacity:");
        double loadCapacity = Inputs.doubleCreatorInput();
        System.out.print("Please enter a brief description of the security features:");
        String protection = scanner.nextLine();
        System.out.print("Please enter density of liquid:");
        double density = Inputs.doubleCreatorInput();
        System.out.print("Please enter name of company/sender:");
        String company = scanner.nextLine();
        System.out.print("Please enter degree of toxicity:");
        double degreeOfToxicity = Inputs.doubleCreatorInput();
        System.out.print("Please enter a brief description of application:");
        String application = scanner.nextLine();
        simulation.addWagon(new LiquidToxicLoadWagon(netWeight, maxWeight, nameOfLoad, loadCapacity, protection, density, company, degreeOfToxicity, application));
        System.out.println("The wagon was successfully created");
    }
}