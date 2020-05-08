package LeserSchreiber.Dead;

import java.util.concurrent.Semaphore;

public class Dead {


    public static int sharedStorage = 0;
    public static int readCount = 0;

    public static Semaphore mut_writeStorage = new Semaphore(1);
    public static Semaphore mut_readCount = new Semaphore(1);
    public static Semaphore mut_queue = new Semaphore(1,true);


    public static void main(String[] args) {

        Leser l1 = new Leser(1);
        Leser l2 = new Leser(2);
        Leser l3 = new Leser(3);
        Schreiber s1 = new Schreiber(1);
        Schreiber s2 = new Schreiber(2);
        s1.start();
        s2.start();
        l3.start();
        l1.start();
        l2.start();
    }
}
