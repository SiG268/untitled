package ProdCons.RaceCondition;

public class Consumer extends Thread {
    private Factory factory=null;
    private Puffer lager=null;

    public Consumer(Factory factory, Puffer lager){
        this.factory=factory;
        this.lager=lager;
    }

    @Override
    public void run() {
        while (true) {
            if(factory!=null&&lager!=null) {
                try {
                    RaceMain.sem_itemsImLager.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String item=lager.getItem();
                System.out.println("Consumer: "+item);
                //System.out.println(lager);
                RaceMain.sem_pufferImLager.release();
                if(item!=null) {
                    try {
                        RaceMain.mut_fabrikSperre.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    factory.consum(item);
                    RaceMain.mut_fabrikSperre.release();
                    try {
                        this.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}
