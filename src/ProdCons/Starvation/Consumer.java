package ProdCons.Starvation;

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
            try {
                StarvationMain.mut_arbeiten.acquire();
                System.out.println("Consumer will Lager");
                StarvationMain.mut_lagerSperre.acquire();
                System.out.println("Consumer hat Lager");
                StarvationMain.sem_itemsImLager.acquire();
                String item = lager.getItem();
                System.out.println("Consumer: " + item);
                System.out.println(lager);
                StarvationMain.sem_pufferImLager.release();
                StarvationMain.mut_lagerSperre.release();
                if (item != null) {
                    System.out.println("Consumer will Fabrik");
                    StarvationMain.mut_fabrikSperre.acquire();
                    System.out.println("Consumer hat Fabrik");
                    factory.consum(item);
                    StarvationMain.mut_fabrikSperre.release();
                    this.sleep(200);
                }
                StarvationMain.mut_arbeiten.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

