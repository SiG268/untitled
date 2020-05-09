package LeserSchreiber.StarvationSolved;

import LeserSchreiber.RaceCondition.Leser;
import LeserSchreiber.RaceCondition.Schreiber;

import java.util.concurrent.Semaphore;

public class StarvationSolved {


    public static int sharedStorage = 0;
    public static int readCount = 0;
    //Integer für die Anzahl an Lesern

    public static Semaphore mut_writeStorage = new Semaphore(1);
    //Semaphore für den gemeinsamen Speicher
    public static Semaphore mut_readCount = new Semaphore(1);
    //Semaphore für die Anzahl an Lesern
    public static Semaphore mut_queue = new Semaphore(1,true);
    //Neue Semaphore Warteschlange


    public static void main(String[] args) {
        //Erstellung von Leser und Schreiber
        LeserSchreiber.RaceCondition.Leser l1 = new LeserSchreiber.RaceCondition.Leser(1);
        LeserSchreiber.RaceCondition.Leser l2 = new LeserSchreiber.RaceCondition.Leser(2);
        LeserSchreiber.RaceCondition.Leser l3 = new Leser(3);
        LeserSchreiber.RaceCondition.Schreiber s1 = new LeserSchreiber.RaceCondition.Schreiber(1);
        LeserSchreiber.RaceCondition.Schreiber s2 = new Schreiber(2);
        //Starten der Threads von Leser und Schreiber
        s1.start();
        s2.start();
        l3.start();
        l1.start();
        l2.start();
    }
}
