package ProdCons.StarvationSolved;

public class Producer extends Thread{
    private Factory factory;
    private Puffer lager;

    public Producer(Factory factory, Puffer lager){
        this.factory=factory;
        this.lager=lager;
    }

    @Override
    public void run() {
        while (true) {
            if (factory != null && lager != null) {
                try {
                    StarvationSolved.sem_pufferImLager.acquire();
                    StarvationSolved.mut_arbeiten.acquire();
                    System.out.println("Producer will Fabrik");
                    StarvationSolved.mut_fabrikSperre.acquire();
                    System.out.println("Producer hat Fabrik");
                    String item = factory.produce();
                    StarvationSolved.mut_fabrikSperre.release();
                    this.sleep(100);
                    System.out.println("Producer will Lager");
                    StarvationSolved.mut_lagerSperre.acquire();
                    System.out.println("Producer hat Lager");
                    System.out.println("Producer: " + item);
                    lager.putItem(item);
                    System.out.println(lager);
                    StarvationSolved.sem_itemsImLager.release();
                    StarvationSolved.mut_lagerSperre.release();
                    StarvationSolved.mut_arbeiten.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
