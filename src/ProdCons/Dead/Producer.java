package ProdCons.Dead;

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
                System.out.println("Producer will Fabrik");
                try {
                    DeadMain.mut_fabrikSperre.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Producer hat Fabrik");
                String item = factory.produce();
                DeadMain.mut_fabrikSperre.release();
                try {
                    this.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Producer will Lager");
                try {
                    DeadMain.mut_lagerSperre.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Producer hat Lager");
                try {
                    DeadMain.sem_pufferImLager.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Producer: "+item);

                lager.putItem(item);

                //System.out.println(lager);
                DeadMain.sem_itemsImLager.release();
                DeadMain.mut_lagerSperre.release();
            }
        }
    }
}
