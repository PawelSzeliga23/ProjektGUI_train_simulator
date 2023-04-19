package Classes.Options;

import Classes.Simulation;
import Classes.Train;

import static Classes.Options.Inputs.incorrectInputCommunicate;

public class SimulationPage {
    public static void simulationPage(Simulation simulation){
        simulationPageGraphics();
        simulationPageFunctions(Inputs.userMenuInput(), simulation);
    }

    private static void simulationPageFunctions(int input, Simulation simulation) {
        switch (input){
            case 1 -> {
                showSpecificTrain(simulation);
                simulationPage(simulation);
            }
            case 2 -> {
                simulation.stopSimulation();
                MainPage.mainPage(simulation);
            }
            default -> {
                System.out.println(incorrectInputCommunicate);
                simulationPage(simulation);
            }
        }
    }

    private static void simulationPageGraphics() {
        System.out.println("------------------------------");
        System.out.println("    Simulation Is Running");
        System.out.println("------------------------------" + '\n');
        System.out.println("1. Show Train");
        System.out.println("2. Stop simulation");
    }
    private static void showSpecificTrain(Simulation simulation){
        simulation.getTrainList().forEach(entry -> System.out.println(entry.getIdentificationNumber() + ". Train"));
        System.out.println("Select train:");
        int input = Inputs.userMenuInput();
        boolean findTrain = false;
        for (Train train : simulation.getTrainList()) {
            if (input == train.getIdentificationNumber()){
                findTrain = true;
                System.out.println("======= "+ train.getIdentificationNumber() + ". Train Report =======");
                System.out.println(train.allBasicInformation());
                break;
            }
        }
        if (!findTrain){
            System.out.println("There is no such trains");
        }
    }
}
