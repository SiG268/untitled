package LeserSchreiber.RaceCondition;

public class RaceCondition {

    public static int sharedStorage = 0;
    //geteilter Speicher

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
