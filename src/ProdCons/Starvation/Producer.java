package ProdCons.Starvation;

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
                    StarvationMain.mut_arbeiten.acquire();
                    System.out.println("Producer will Fabrik");
                    StarvationMain.mut_fabrikSperre.acquire();
                    System.out.println("Producer hat Fabrik");
                    String item = factory.produce();
                    StarvationMain.mut_fabrikSperre.release();
                    this.sleep(100);
                    System.out.println("Producer will Lager");
                    StarvationMain.mut_lagerSperre.acquire();
                    System.out.println("Producer hat Lager");
                    StarvationMain.sem_pufferImLager.acquire();
                    System.out.println("Producer: " + item);
                    lager.putItem(item);
                    System.out.println(lager);
                    StarvationMain.sem_itemsImLager.release();
                    StarvationMain.mut_lagerSperre.release();
                    StarvationMain.mut_arbeiten.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
