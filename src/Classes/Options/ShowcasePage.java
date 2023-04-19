package Classes.Options;

import Classes.Simulation;

public class ShowcasePage {
    public static void showcasePage(Simulation simulation, int menuType) {
        showcasePageGraphics();
        showcasePageFunctions(Inputs.userMenuInput(), simulation, menuType);
    }

    public static void showcasePageGraphics() {
        System.out.println("------------------");
        System.out.println("     Showcase");
        System.out.println("------------------" + '\n');
        System.out.println("Choose one option:");
        System.out.println("1. Show all free Wagons");
        System.out.println("2. Show all free Locomotives");
        System.out.println("3. Show all Trains");
        System.out.println("4. Show all Stations");
        System.out.println("5. Show all Connections");
        System.out.println("6. Back");
    }

    public static void showcasePageFunctions(int input, Simulation simulation, int menuType) {
        switch (input) {
            case 1 -> {
                simulation.showWagons();
                showcasePage(simulation, menuType);
            }
            case 2 -> {
                simulation.showFreeLocomotives();
                showcasePage(simulation, menuType);
            }
            case 3 -> {
                simulation.showTrains();
                showcasePage(simulation, menuType);
            }
            case 4 -> {
                simulation.showStations();
                showcasePage(simulation ,menuType);
            }
            case 5 -> {
                simulation.showAllConnections();
                showcasePage(simulation, menuType);
            }
            case 6 -> {
                switch (menuType){
                    case 1 -> StartingPage.startingPage(simulation);
                    case 2 -> StartingPage.startingPageWithoutRandom(simulation);
                    case 3 -> MainPage.mainPage(simulation);
                }
            }
            default -> {
                System.out.println(Inputs.incorrectInputCommunicate);
                showcasePage(simulation, menuType);
            }
        }
    }
}
