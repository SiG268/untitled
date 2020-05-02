package LeserSchreiber.RaceConditionSolved;

import java.util.concurrent.Semaphore;

public class RaceConditionSolved {
    public static Semaphore sem_Schreiber = new Semaphore(1);
    public static Semaphore mut_arbeiten = new Semaphore(3);
    public static Semaphore sem_Leser = new Semaphore(0);
    public static final DateiSystem DS = new DateiSystem();
    public static int count;
    public static int read_count = 0;

    public static void main(String[] args) {


        //Datei datei = new Datei(1);
        DS.create("/root/users/user1/desktop/", "datei1");

        Leser l1 = new Leser();
        Leser l2 = new Leser();
        Leser l3 = new Leser();
        Schreiber s1 = new Schreiber(100);
        Schreiber s2 = new Schreiber(12);
        s1.start();
        s2.start();
        l3.start();
        l1.start();
        l2.start();

    }
}
