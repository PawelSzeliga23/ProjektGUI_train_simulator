package Classes.Options;

import Classes.RandomSimulation;
import Classes.Simulation;

import static Classes.Options.Inputs.incorrectInputCommunicate;
import static Classes.Options.Inputs.userMenuInput;

public class StartingPage {
    public static int menuTypeWithoutRandom = 2;
    public static int menuType = 1;

    public static void startingPage(Simulation simulation) {
        startingPageGraphics();
        startingPageFunctions(userMenuInput(), simulation);
    }

    public static void startingPageWithoutRandom(Simulation simulation) {
        startingPageGraphicsWithoutRandom();
        startingPageFunctionsWithoutRandom(userMenuInput(), simulation);
    }

    public static void startingPageGraphics() {
        System.out.println("------------------");
        System.out.println("Railroad Simulator");
        System.out.println("------------------" + '\n');
        System.out.println("Choose one option:");
        System.out.println("1. Start Simulation");
        System.out.println("2. Creator");
        System.out.println("3. Functions");
        System.out.println("4. Showcase");
        System.out.println("5. Random Simulation");
        System.out.println("6. Exit" + '\n');
    }

    public static void startingPageGraphicsWithoutRandom() {
        System.out.println("------------------");
        System.out.println("Railroad Simulator");
        System.out.println("------------------" + '\n');
        System.out.println("Choose one option:");
        System.out.println("1. Start Simulation");
        System.out.println("2. Creator");
        System.out.println("3. Functions");
        System.out.println("4. Showcase");
        System.out.println("5. Exit" + '\n');
    }


    public static void startingPageFunctions(int input, Simulation simulation) {
        switch (input) {
            case 1 -> {
                simulation.startSimulation();
                SimulationPage.simulationPage(simulation);
            }
            case 2 -> CreatorPage.creatorPage(simulation, menuType);
            case 3 -> FunctionsPage.functionsPage(simulation,menuType);
            case 4 -> ShowcasePage.showcasePage(simulation ,menuType);
            case 5 -> {
                RandomSimulation randomSimulation = new RandomSimulation();
                randomSimulation.prepareAssets();
                startingPageWithoutRandom(randomSimulation.randomSimulation());
            }
            case 6 -> {
                System.exit(0);
            }
            default -> {
                System.out.println(incorrectInputCommunicate);
                startingPage(simulation);
            }
        }
    }

    public static void startingPageFunctionsWithoutRandom(int input, Simulation simulation) {
        switch (input) {
            case 1 -> {
                simulation.startSimulation();
                SimulationPage.simulationPage(simulation);
            }
            case 2 -> CreatorPage.creatorPage(simulation, menuTypeWithoutRandom);
            case 3 -> FunctionsPage.functionsPage(simulation, menuTypeWithoutRandom);
            case 4 -> ShowcasePage.showcasePage(simulation , menuTypeWithoutRandom);
            case 5 -> {
                System.exit(0);
            }
            default -> {
                System.out.println(incorrectInputCommunicate);
                startingPageWithoutRandom(simulation);
            }
        }
    }

}
