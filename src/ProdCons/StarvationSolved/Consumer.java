package ProdCons.StarvationSolved;

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
                    StarvationSolved.sem_itemsImLager.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    StarvationSolved.mut_arbeiten.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //System.out.println("Consumer will Lager");
                try {
                    StarvationSolved.mut_lagerSperre.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //System.out.println("Consumer hat Lager");

                String item=lager.getItem();
                System.out.println("Consumer: "+item);
                System.out.println(lager);
                StarvationSolved.sem_pufferImLager.release();
                StarvationSolved.mut_lagerSperre.release();

                if(item!=null) {
                    //System.out.println("Consumer will Fabrik");
                    try {
                        StarvationSolved.mut_fabrikSperre.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //System.out.println("Consumer hat Fabrik");
                    factory.consum(item);
                    StarvationSolved.mut_fabrikSperre.release();
                    try {
                        this.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    StarvationSolved.mut_arbeiten.release();
                }
            }
        }
    }
}
