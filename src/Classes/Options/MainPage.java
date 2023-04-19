package Classes.Options;

import Classes.Simulation;

import static Classes.Options.Inputs.incorrectInputCommunicate;

public class MainPage {
    public static int menuType = 3;
    public static void mainPage(Simulation simulation){
        mainPageGraphics();
        mainPageFunctions(Inputs.userMenuInput(),simulation);
    }

    private static void mainPageFunctions(int userMenuInput, Simulation simulation) {
        switch (userMenuInput) {
            case 1 -> {
                simulation.resumeSimulation();
                SimulationPage.simulationPage(simulation);
            }
            case 2 -> CreatorPage.creatorPage(simulation, menuType);
            case 3 -> FunctionsPage.functionsPage(simulation, menuType);
            case 4 -> ShowcasePage.showcasePage(simulation , menuType);
            case 5 -> {
                System.exit(0);
            }
            default -> {
                System.out.println(incorrectInputCommunicate);
                mainPage(simulation);
            }
        }
    }

    private static void mainPageGraphics() {
        System.out.println("------------------");
        System.out.println("Railroad Simulator");
        System.out.println("------------------" + '\n');
        System.out.println("Choose one option:");
        System.out.println("1. Resume Simulation");
        System.out.println("2. Creator");
        System.out.println("3. Functions");
        System.out.println("4. Showcase");
        System.out.println("5. Exit" + '\n');
    }

}
