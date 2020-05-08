package ProdCons.RaceConditionSolved;

import java.util.concurrent.Semaphore;

public class RaceSolvedMain {
    //Puffergröße
    final static int PUFFERSIZE = 10;
    //Semaphore für die Consumer (Begrenzung wenn Lager leer ist)
    public static Semaphore sem_itemsImLager = new Semaphore(0);
    //Semaphore für die Producer (Begrenzung wenn Lager voll ist)
    public static Semaphore sem_pufferImLager = new Semaphore(PUFFERSIZE);
    //Mutex damit Consumer/Producer nicht gleichzeitig auf die Fabrik zugreifen
    public static Semaphore mut_fabrikSperre = new Semaphore(1);
    //Mutex damit Consumer/Producer nicht gleichzeitig auf das Lager zugreifen
    public static Semaphore mut_lagerSperre = new Semaphore(1);


    public static void main(String[] args) {
        //Erstellung von Producer, Consumer, Lager und Factory
        Puffer lager = new Puffer();
        Factory factory = new Factory();
        Consumer c = new Consumer(factory,lager);
        Producer p = new Producer(factory,lager);
        //Starten der Producer und Consumer Threads
        p.start();
        c.start();
    }
}
