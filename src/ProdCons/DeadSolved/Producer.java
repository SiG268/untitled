package ProdCons.DeadSolved;

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
            try {
                DeadSolvedMain.mut_arbeiten.acquire();
                System.out.println("Producer will Fabrik");
                DeadSolvedMain.mut_fabrikSperre.acquire();
                System.out.println("Producer hat Fabrik");
                String item = factory.produce();
                DeadSolvedMain.mut_fabrikSperre.release();
                this.sleep(100);
                System.out.println("Producer will Lager");
                DeadSolvedMain.mut_lagerSperre.acquire();
                System.out.println("Producer hat Lager");
                DeadSolvedMain.sem_pufferImLager.acquire();
                System.out.println("Producer: " + item);
                lager.putItem(item);
                System.out.println(lager);
                DeadSolvedMain.sem_itemsImLager.release();
                DeadSolvedMain.mut_lagerSperre.release();
                DeadSolvedMain.mut_arbeiten.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
