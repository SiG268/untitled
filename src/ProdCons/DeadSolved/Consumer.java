package ProdCons.DeadSolved;

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
                    DeadSolvedMain.mut_arbeiten.acquire();          //Will arbeiten
                    System.out.println("Consumer will Lager");
                    RaceSolvedMain.mut_lagerSperre.acquire();
                    System.out.println("Consumer hat Lager");
                    RaceSolvedMain.sem_itemsImLager.acquire();      //Überprüfung ob Items im Lager sind
                    String item = lager.getItem();
                    System.out.println("Consumer: " + item);
                    //System.out.println(lager);
                    RaceSolvedMain.sem_pufferImLager.release();
                    RaceSolvedMain.mut_lagerSperre.release();
                    if (item != null) {
                        System.out.println("Consumer will Fabrik");
                        RaceSolvedMain.mut_fabrikSperre.acquire();
                        System.out.println("Consumer hat Fabrik");
                        factory.consum(item);
                        RaceSolvedMain.mut_fabrikSperre.release();
                        this.sleep(200);
                    }
                    DeadSolvedMain.mut_arbeiten.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
