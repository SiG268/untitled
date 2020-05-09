package LeserSchreiber.DeadSolved;


import java.util.concurrent.Semaphore;

public class DeadSolved {

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
        Leser l1 = new Leser(1);
        Leser l2 = new Leser(2);
        Leser l3 = new Leser(3);
        Schreiber s1 = new Schreiber(1);
        Schreiber s2 = new Schreiber(2);
        //Starten der Threads von Leser und Schreiber
        s1.start();
        s2.start();
        l3.start();
        l1.start();
        l2.start();
    }
}
