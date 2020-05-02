package LeserSchreiber.RaceCondition;

import java.util.concurrent.Semaphore;

public class RaceCondition {
    public static Semaphore sem_Schreiber = new Semaphore(1);
    public static Semaphore sem_Leser = new Semaphore(0);
    public static final DateiSystem DS = new DateiSystem();
    public static int count;
    public static void main(String[] args) {


        //Datei datei = new Datei(1);
        DS.create("/root/users/user1/desktop/", "datei1");

        Leser l1 = new Leser();
        Leser l2 = new Leser();
        Leser l3 = new Leser();
        Schreiber s1 = new Schreiber(0);
        Schreiber s2 = new Schreiber(1);
        s1.start();
        s2.start();
        l3.start();
        try {
            new Thread().sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        l1.start();
        try {
            new Thread().sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        l2.start();

    }
}
