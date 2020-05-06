package ProdCons.RaceCondition;

public class Consumer extends Thread {
    private Factory factory;
    private Puffer lager;

    public Consumer(Factory factory, Puffer lager) {
        this.factory = factory;
        this.lager = lager;
    }

    @Override
    public void run() {
        while (true) {
            if (factory != null && lager != null) {
                try {
                    RaceMain.sem_itemsImLager.acquire();
                    String item = lager.getItem();
                    System.out.println("Consumer: " + item);
                    RaceMain.sem_pufferImLager.release();
                    if (item != null) {
                        RaceMain.mut_fabrikSperre.acquire();
                        factory.consum(item);
                        RaceMain.mut_fabrikSperre.release();
                        this.sleep(2);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}

