package ProdCons.RaceCondition;

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
            if(factory!=null&&lager!=null) {
                try {

                    //Sperre Fabrik und hole Item aus der Fabrik
                    RaceMain.mut_fabrikSperre.acquire();
                    String item = factory.produce();
                    RaceMain.mut_fabrikSperre.release();
                    //Produktionszeit (hier sehr niedrig-> die Häufigkeit des Auftreten der RaceCondition wird erhöhen)
                    this.sleep(1);
                    //Einlagerung wenn noch Platz im Lager ist
                    RaceMain.sem_pufferImLager.acquire();
                    //Einlagerung
                    lager.putItem(item);
                    System.out.println("Producer: "+item);
                    System.out.println(lager);
                    //Erhöhe die Itemsemaphore für den Consumer
                    RaceMain.sem_itemsImLager.release();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
