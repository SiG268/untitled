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
                    //Überprüfe ob noch Platz im Lager ist
                    StarvationSolved.sem_pufferImLager.acquire();

                    //Versuche zu arbeiten
                    StarvationSolved.mut_arbeiten.acquire();
                    //Sperre Fabrik und hole Item aus der Fabrik
                    System.out.println("Producer will Fabrik");
                    StarvationSolved.mut_fabrikSperre.acquire();
                    System.out.println("Producer hat Fabrik");
                    String item = factory.produce();
                    StarvationSolved.mut_fabrikSperre.release();

                    //Produktionszeit (hier sehr niedrig-> die Häufigkeit des Auftreten der RaceCondition wird erhöhen)
                    this.sleep(1);

                    //Sperre das Lager
                    System.out.println("Producer will Lager");
                    StarvationSolved.mut_lagerSperre.acquire();
                    System.out.println("Producer hat Lager");

                    //Einlagerung
                    lager.putItem(item);
                    System.out.println("Producer: "+item);
                    System.out.println(lager);

                    //Erhöhe die Itemsemaphore für den Consumer
                    StarvationSolved.sem_itemsImLager.release();

                    //Löse die Lagersperre
                    StarvationSolved.mut_lagerSperre.release();
                    //Anderer Thread darf arbeiten
                    StarvationSolved.mut_arbeiten.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
