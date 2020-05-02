package LeserSchreiber.RaceCondition;

import java.util.concurrent.Semaphore;

public class RaceCondition {
    public static Semaphore sem_Schreiber = new Semaphore(1);
    public static Semaphore sem_Leser = new Semaphore(0);

    public static void main(String[] args) {
        Speicher datei = new Speicher();
        Speicher zwischenSpeicher = new Speicher();

        Leser l = new Leser(datei);
        Schreiber s = new Schreiber(datei);
    }
}
