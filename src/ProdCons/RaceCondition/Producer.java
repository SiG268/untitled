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

                    RaceMain.mut_fabrikSperre.acquire();
                    String item = factory.produce();
                    RaceMain.mut_fabrikSperre.release();
                    this.sleep(1);
                    RaceMain.sem_pufferImLager.acquire();
                    lager.putItem(item);
                    System.out.println("Producer: "+item);
                    System.out.println(lager);
                    RaceMain.sem_itemsImLager.release();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
