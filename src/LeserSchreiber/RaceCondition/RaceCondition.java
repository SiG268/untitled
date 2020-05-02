package LeserSchreiber.RaceCondition;

import java.util.concurrent.Semaphore;

public class RaceCondition {
    public static Semaphore sem_Schreiber = new Semaphore(1);
    public static Semaphore sem_Leser = new Semaphore(0);
    public static final DateiSystem DS = new DateiSystem();
    public static void main(String[] args) {

        //Datei datei = new Datei(1);
        DS.create("/root/users/user1/desktop/", "datei1");
        System.out.println(DS.getDatei("/root/users/user1/desktop/datei1"));
    }
}
