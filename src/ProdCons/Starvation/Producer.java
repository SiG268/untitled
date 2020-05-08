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
                    //Versuche zu arbeiten
                    StarvationMain.mut_arbeiten.acquire();
                    //Sperre Fabrik und hole Item aus der Fabrik
                    System.out.println("Producer will Fabrik");
                    StarvationMain.mut_fabrikSperre.acquire();
                    System.out.println("Producer hat Fabrik");
                    String item = factory.produce();
                    StarvationMain.mut_fabrikSperre.release();

                    //Produktionszeit (hier sehr niedrig-> die Häufigkeit des Auftreten der RaceCondition wird erhöhen)
                    this.sleep(1);

                    //Sperre das Lager
                    System.out.println("Producer will Lager");
                    StarvationMain.mut_lagerSperre.acquire();
                    System.out.println("Producer hat Lager");

                    //Einlagerung wenn noch Platz im Lager ist
                    StarvationMain.sem_pufferImLager.acquire();

                    //Einlagerung
                    lager.putItem(item);
                    System.out.println("Producer: "+item);
                    System.out.println(lager);

                    //Erhöhe die Itemsemaphore für den Consumer
                    StarvationMain.sem_itemsImLager.release();

                    //Löse die Lagersperre
                    StarvationMain.mut_lagerSperre.release();
                    //Anderer Thread darf arbeiten
                    StarvationMain.mut_arbeiten.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
