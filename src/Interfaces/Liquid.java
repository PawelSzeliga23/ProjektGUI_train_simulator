package Interfaces;

public interface Liquid {
    default void spilling(){
        System.out.println("Spilling!!!");
    }
    //nawiazanie do rozlewajacych sie szybow naftowych
    default void communicate(){
        System.out.println("bul, bul, bul");
    }
}
