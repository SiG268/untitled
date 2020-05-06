package ProdCons.RaceConditionSolved;

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
                try{
                System.out.println("Producer will Fabrik");
                RaceSolvedMain.mut_fabrikSperre.acquire();
                System.out.println("Producer hat Fabrik");
                String item = factory.produce();
                RaceSolvedMain.mut_fabrikSperre.release();
                this.sleep(100);
                System.out.println("Producer will Lager");
                RaceSolvedMain.mut_lagerSperre.acquire();
                System.out.println("Producer hat Lager");
                RaceSolvedMain.sem_pufferImLager.acquire();
                System.out.println("Producer: " + item);
                lager.putItem(item);
                //System.out.println(lager);
                RaceSolvedMain.sem_itemsImLager.release();
                RaceSolvedMain.mut_lagerSperre.release();
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
