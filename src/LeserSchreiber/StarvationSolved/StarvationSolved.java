package LeserSchreiber.StarvationSolved;

import java.util.concurrent.Semaphore;

public class StarvationSolved {

    public static int sharedStorage = 0;
    public static int readCount = 0;
    public static int writeCount = 0;
    public static boolean mode = false;

    public static Semaphore mut_writeTakt = new Semaphore(0);
    public static Semaphore mut_readTakt = new Semaphore(0);

    public static Semaphore mut_writeStorage = new Semaphore(1);
    public static Semaphore mut_readCount = new Semaphore(1);
    public static Semaphore mut_read = new Semaphore(1,true);
    public static Semaphore mut_write = new Semaphore(1,true);
    public static Semaphore mut_wantread = new Semaphore(1);
    public static Semaphore mut_writeCount = new Semaphore(1);
    public static Semaphore mut_wantReadCount = new Semaphore(1);
    public static int wantReadCount = 0;
    public static Semaphore mut_wantWriteCount = new Semaphore(1);
    public static int wantWriteCount = 0;


    public static void main(String[] args) {
        Takt t = new Takt();
        Leser l1 = new Leser(1);
        Leser l2 = new Leser(2);
        Leser l3 = new Leser(3);
        Schreiber s1 = new Schreiber(1);
        Schreiber s2 = new Schreiber(2);
        t.start();
        s1.start();
        s2.start();
        l3.start();
        l1.start();
        l2.start();
    }
}
