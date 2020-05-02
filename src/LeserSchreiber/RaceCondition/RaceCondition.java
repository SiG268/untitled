package LeserSchreiber.RaceCondition;

import java.util.concurrent.Semaphore;

public class RaceCondition {
    public static Semaphore sem_Schreiber = new Semaphore(1);
    public static Semaphore sem_Leser = new Semaphore(0);

    public static void main(String[] args) {

        Datei datei = new Datei(1);
        FileDescriptor fd = new FileDescriptor(datei);
        InodeTable speicher = new InodeTable(datei,fd);

        Leser l = new Leser(speicher,fd);
        Schreiber s = new Schreiber(speicher,fd);
    }
}
