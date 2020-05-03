package LeserSchreiberNew.Dead;

import java.util.concurrent.Semaphore;

public class RaceCondition {

    public static Semaphore mut_data = new Semaphore(1);
    public static void main(String[] args) {

        Datei datei = new Datei();
        Daten daten = new Daten("halöp");

        Leser l1 = new Leser(datei, daten);
        Leser l2 = new Leser(datei, daten);
        Leser l3 = new Leser(datei, daten);
        Schreiber s1 = new Schreiber(datei, daten,1);
        Schreiber s2 = new Schreiber(datei, daten,20);
        s1.start();
        s2.start();
        l3.start();
    }
}
