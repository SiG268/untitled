package ProdCons.Dead;

import LeserSchreiber.Dead.Dead;
import ProdCons.RaceConditionSolved.RaceSolvedMain;

public class Producer extends Thread {
    private Factory factory;
    private Puffer lager;

    public Producer(Factory factory, Puffer lager) {
        this.factory = factory;
        this.lager = lager;
    }

    @Override
    public void run() {
        while (true) {
            if (factory != null && lager != null) {
                try {
                    System.out.println("Producer will Fabrik");
                    DeadMain.mut_fabrikSperre.acquire();
                    System.out.println("Producer hat Fabrik");
                    String item = factory.produce();
                    DeadMain.mut_fabrikSperre.release();
                    this.sleep(100);
                    System.out.println("Producer will Lager");
                    DeadMain.mut_lagerSperre.acquire();
                    System.out.println("Producer hat Lager");
                    DeadMain.sem_pufferImLager.acquire();
                    System.out.println("Producer: " + item);
                    lager.putItem(item);
                    //System.out.println(lager);
                    DeadMain.sem_itemsImLager.release();
                    DeadMain.mut_lagerSperre.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
