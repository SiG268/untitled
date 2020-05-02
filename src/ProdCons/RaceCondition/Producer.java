package ProdCons.RaceCondition;

public class Producer extends Thread{
    private Factory factory=null;
    private Puffer lager=null;

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
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String item = factory.produce();
                RaceMain.mut_fabrikSperre.release();
                try {
                    this.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    RaceMain.sem_pufferImLager.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Producer: "+item);
                lager.putItem(item);
                //System.out.println(lager);
                RaceMain.sem_itemsImLager.release();
            }
        }
    }
}
