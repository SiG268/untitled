package ProdCons.Starvation;

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
                    Starvation.mut_arbeiten.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Producer will Fabrik");
                try {
                    Starvation.mut_fabrikSperre.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Producer hat Fabrik");
                String item = factory.produce();
                Starvation.mut_fabrikSperre.release();
                try {
                    this.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Producer will Lager");
                try {
                    Starvation.mut_lagerSperre.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Producer hat Lager");
                try {
                    Starvation.sem_pufferImLager.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Producer: "+item);

                lager.putItem(item);

                System.out.println(lager);
                Starvation.sem_itemsImLager.release();
                Starvation.mut_lagerSperre.release();
                Starvation.mut_arbeiten.release();
            }
        }
    }
}
