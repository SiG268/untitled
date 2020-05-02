package ProdCons.Dead;

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
                System.out.println("Consumer will Lager");
                try {
                    DeadMain.mut_lagerSperre.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Consumer hat Lager");
                try {
                    DeadMain.sem_itemsImLager.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String item=lager.getItem();
                System.out.println("Consumer: "+item);
                //System.out.println(lager);
                DeadMain.sem_pufferImLager.release();
                DeadMain.mut_lagerSperre.release();

                if(item!=null) {
                    System.out.println("Consumer will Fabrik");
                    try {
                        DeadMain.mut_fabrikSperre.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Consumer hat Fabrik");
                    factory.consum(item);
                    DeadMain.mut_fabrikSperre.release();
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
