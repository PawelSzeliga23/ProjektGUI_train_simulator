import Classes.Options.CreatorPage;
import Classes.Options.FunctionsPage;
import Classes.Options.Inputs;
import Classes.Simulation;

public class Presentation {
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        // klasa symulacja przechowuje obiekty i obsluguje wiekszosc funkcjonalnosci projektu
        // z symulacji mozemy rowniez obiekty usunac lub wyswietlic zawarotsc symulacji po wykonaniu odpowiednich metod
        // wiekszoc metod na tworzenie obiektow sa metodami prywatnymi gdyz uzywam ich tylko klasie CreatorPage
        // na potrzeby Presentation.java przerobilem je na publiczne metody
        CreatorPage.locomotiveCreator(simulation);
        CreatorPage.stationCreator(simulation);
        CreatorPage.connectionCreator(simulation);
        CreatorPage.createWagonPage(simulation, Inputs.userMenuInput());
        //weikszosc metod na poszczegulne funkcjonalosci rowniez sa metodami prywatnymi gdzyz wykozystuje je jedynie w klasie FunctionsPage
        // na potrzeby Presentation.java przerobilem je na publiczne metody
        FunctionsPage.attachWagonsToLocomotive(simulation);
        FunctionsPage.loadWagon(simulation);
        FunctionsPage.removeFreeLocomotive(simulation);
        FunctionsPage.removeStation(simulation);
        FunctionsPage.removeTrain(simulation);
        FunctionsPage.removeWagon(simulation);
    }
}
