package ProdCons.DeadSolved;

public class Producer extends Thread {
    private Factory factory;
    private Puffer lager;

    public Producer(Factory factory, Puffer lager) {
        this.factory = factory;
        this.lager = lager;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //Versuche zu arbeiten
                DeadSolvedMain.mut_arbeiten.acquire();
                //Sperre Fabrik und hole Item aus der Fabrik
                System.out.println("Producer will Fabrik");
                DeadSolvedMain.mut_fabrikSperre.acquire();
                System.out.println("Producer hat Fabrik");
                String item = factory.produce();
                DeadSolvedMain.mut_fabrikSperre.release();

                //Produktionszeit (hier sehr niedrig-> die Häufigkeit des Auftreten der RaceCondition wird erhöhen)
                this.sleep(1);

                //Sperre das Lager
                System.out.println("Producer will Lager");
                DeadSolvedMain.mut_lagerSperre.acquire();
                System.out.println("Producer hat Lager");

                //Einlagerung wenn noch Platz im Lager ist
                DeadSolvedMain.sem_pufferImLager.acquire();

                //Einlagerung
                lager.putItem(item);
                System.out.println("Producer: "+item);
                System.out.println(lager);

                //Erhöhe die Itemsemaphore für den Consumer
                DeadSolvedMain.sem_itemsImLager.release();

                //Löse die Lagersperre
                DeadSolvedMain.mut_lagerSperre.release();
                //Anderer Thread darf arbeiten
                DeadSolvedMain.mut_arbeiten.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
