package LeserSchreiber.DeadSolved;

import java.util.concurrent.Semaphore;

public class DeadSolved {
    public static Semaphore sem_Leser = new Semaphore(1);
    public static Semaphore mut_wc = new Semaphore(1);
    public static Semaphore sem_Schreiber = new Semaphore(1);
    public static Semaphore mut_rc = new Semaphore(1);
    public static final DateiSystem DS = new DateiSystem();
    public static int count;
    public static int read_count = 0;
    public static int write_counter = 0;

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
        l1.start();
        l2.start();

    }
}
