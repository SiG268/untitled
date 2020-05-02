package ProdCons.RaceConditionSolved;

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
                    RaceSolvedMain.mut_fabrikSperre.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Producer hat Fabrik");
                String item = factory.produce();
                RaceSolvedMain.mut_fabrikSperre.release();
                try {
                    this.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Producer will Lager");
                try {
                    RaceSolvedMain.mut_lagerSperre.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Producer hat Lager");
                try {
                    RaceSolvedMain.sem_pufferImLager.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Producer: "+item);

                lager.putItem(item);

                //System.out.println(lager);
                RaceSolvedMain.sem_itemsImLager.release();
                RaceSolvedMain.mut_lagerSperre.release();
            }
        }
    }
}
