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
                //Versuche zu arbeiten
                StarvationMain.mut_arbeiten.acquire();
                //Sperre das Lager
                System.out.println("Consumer will Lager");
                StarvationMain.mut_lagerSperre.acquire();
                System.out.println("Consumer hat Lager");

                //Überprüfe ob Items im Lager sind
                StarvationMain.sem_itemsImLager.acquire();

                //Hole Item aus dem Lager
                String item = lager.getItem();
                System.out.println("Consumer: " + item);
                //System.out.println(lager);
                StarvationMain.sem_pufferImLager.release();

                //Lager wieder freigeben
                StarvationMain.mut_lagerSperre.release();

                //Nutze die Fabrik um das Item zu konsumieren
                if (item != null) {
                    System.out.println("Consumer will Fabrik");
                    StarvationMain.mut_fabrikSperre.acquire();
                    System.out.println("Consumer hat Fabrik");
                    factory.consum(item);
                    StarvationMain.mut_fabrikSperre.release();

                    //Zeit zum konsumieren (hier sehr niedrig-> die Häufigkeit des Auftreten der RaceCondition wird erhöhen)
                    this.sleep(2);
                }
                //Anderer Thread darf arbeiten
                StarvationMain.mut_arbeiten.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

