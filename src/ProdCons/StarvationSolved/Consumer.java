package ProdCons.StarvationSolved;

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
                    StarvationSolved.sem_itemsImLager.acquire();
                    StarvationSolved.mut_arbeiten.acquire();
                    System.out.println("Consumer will Lager");
                    StarvationSolved.mut_lagerSperre.acquire();
                    System.out.println("Consumer hat Lager");
                    String item = lager.getItem();
                    System.out.println("Consumer: " + item);
                    System.out.println(lager);
                    StarvationSolved.sem_pufferImLager.release();
                    StarvationSolved.mut_lagerSperre.release();
                    if (item != null) {
                        System.out.println("Consumer will Fabrik");
                        StarvationSolved.mut_fabrikSperre.acquire();
                        System.out.println("Consumer hat Fabrik");
                        factory.consum(item);
                        StarvationSolved.mut_fabrikSperre.release();
                        this.sleep(200);
                    }
                    StarvationSolved.mut_arbeiten.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        }
    }
}
