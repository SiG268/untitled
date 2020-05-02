package ProdCons.Starvation;

import java.util.concurrent.Semaphore;

public class Starvation {
    final static int PUFFERSIZE = 10;
    public static Semaphore sem_itemsImLager = new Semaphore(0);
    public static Semaphore sem_pufferImLager = new Semaphore(PUFFERSIZE);
    public static Semaphore mut_fabrikSperre = new Semaphore(1);
    public static Semaphore mut_lagerSperre = new Semaphore(1);
    public static Semaphore mut_arbeiten = new Semaphore(1);

    public static void main(String[] args) {
        Puffer lager = new Puffer();
        Factory factory = new Factory();
        Consumer c = new Consumer(factory,lager);
        Producer p = new Producer(factory,lager);
        p.start();
        c.start();
    }
}
