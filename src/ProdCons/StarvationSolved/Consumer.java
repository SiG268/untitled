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
                    //Überprüfe ob Items im Lager sind
                    StarvationSolved.sem_itemsImLager.acquire();

                    //Versuche zu arbeiten
                    StarvationSolved.mut_arbeiten.acquire();
                    //Sperre das Lager
                    System.out.println("Consumer will Lager");
                    StarvationSolved.mut_lagerSperre.acquire();
                    System.out.println("Consumer hat Lager");

                    //Hole Item aus dem Lager
                    String item = lager.getItem();
                    System.out.println("Consumer: " + item);
                    //System.out.println(lager);
                    StarvationSolved.sem_pufferImLager.release();

                    //Lager wieder freigeben
                    StarvationSolved.mut_lagerSperre.release();

                    //Nutze die Fabrik um das Item zu konsumieren
                    if (item != null) {
                        System.out.println("Consumer will Fabrik");
                        StarvationSolved.mut_fabrikSperre.acquire();
                        System.out.println("Consumer hat Fabrik");
                        factory.consum(item);
                        StarvationSolved.mut_fabrikSperre.release();

                        //Zeit zum konsumieren (hier sehr niedrig-> die Häufigkeit des Auftreten der RaceCondition wird erhöhen)
                        this.sleep(2);
                    }
                    //Anderer Thread darf arbeiten
                    StarvationSolved.mut_arbeiten.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        }
    }
}
