package ProdCons.RaceCondition;

import java.util.concurrent.Semaphore;

public class RaceMain {
    final static int PUFFERSIZE = 10;
    public static Semaphore sem_itemsImLager = new Semaphore(0);
    public static Semaphore sem_pufferImLager = new Semaphore(PUFFERSIZE);
    public static Semaphore mut_fabrikSperre = new Semaphore(1);


    public static void main(String[] args) {
        Puffer lager = new Puffer();
        Factory factory = new Factory();
        Consumer c = new Consumer(factory,lager);
        Producer p = new Producer(factory,lager);
        p.start();
        c.start();
    }
}
