package ProdCons.Dead;

import ProdCons.RaceConditionSolved.RaceSolvedMain;

public class Consumer extends Thread {
    private Factory factory;
    private Puffer lager;

    public Consumer(Factory factory, Puffer lager){
        this.factory=factory;
        this.lager=lager;
    }

    @Override
    public void run() {
        while (true) {
            if (factory != null && lager != null) {
                try {
                    System.out.println("Consumer will Lager");
                    DeadMain.mut_lagerSperre.acquire();
                    System.out.println("Consumer hat Lager");
                    DeadMain.sem_itemsImLager.acquire();
                    String item = lager.getItem();
                    System.out.println("Consumer: " + item);
                    //System.out.println(lager);
                    DeadMain.sem_pufferImLager.release();
                    DeadMain.mut_lagerSperre.release();
                    if (item != null) {
                        System.out.println("Consumer will Fabrik");
                        DeadMain.mut_fabrikSperre.acquire();
                        System.out.println("Consumer hat Fabrik");
                        factory.consum(item);
                        DeadMain.mut_fabrikSperre.release();
                        this.sleep(200);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
