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
                    //Überprüfe ob Items im Lager sind
                    RaceMain.sem_itemsImLager.acquire();
                    //Hole Item aus dem Lager
                    String item = lager.getItem();
                    System.out.println("Consumer: " + item);
                    //Setzte den Lagerpuffer frei für den Producer
                    RaceMain.sem_pufferImLager.release();

                    //Nutze die Fabrik um das Item zu konsumieren
                    if (item != null) {
                        RaceMain.mut_fabrikSperre.acquire();
                        factory.consum(item);
                        RaceMain.mut_fabrikSperre.release();
                        //Zeit zum konsumieren (hier sehr niedrig-> die Häufigkeit des Auftreten der RaceCondition wird erhöhen)
                        this.sleep(2);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}

