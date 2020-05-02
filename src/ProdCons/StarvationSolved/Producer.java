package ProdCons.StarvationSolved;

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
                    StarvationSolved.sem_pufferImLager.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    StarvationSolved.mut_arbeiten.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //System.out.println("Producer will Fabrik");
                try {
                    StarvationSolved.mut_fabrikSperre.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //System.out.println("Producer hat Fabrik");
                String item = factory.produce();
                StarvationSolved.mut_fabrikSperre.release();
                try {
                    this.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //System.out.println("Producer will Lager");
                try {
                    StarvationSolved.mut_lagerSperre.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //System.out.println("Producer hat Lager");

                System.out.println("Producer: "+item);

                lager.putItem(item);

                System.out.println(lager);
                StarvationSolved.sem_itemsImLager.release();
                StarvationSolved.mut_lagerSperre.release();
                StarvationSolved.mut_arbeiten.release();
            }
        }
    }
}
