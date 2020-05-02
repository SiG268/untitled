package ProdCons.DeadSolved;

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
                    DeadSolvedMain.mut_arbeiten.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Producer will Fabrik");
                try {
                    DeadSolvedMain.mut_fabrikSperre.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Producer hat Fabrik");
                String item = factory.produce();
                DeadSolvedMain.mut_fabrikSperre.release();
                try {
                    this.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Producer will Lager");
                try {
                    DeadSolvedMain.mut_lagerSperre.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Producer hat Lager");
                try {
                    DeadSolvedMain.sem_pufferImLager.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Producer: "+item);

                lager.putItem(item);

                //System.out.println(lager);
                DeadSolvedMain.sem_itemsImLager.release();
                DeadSolvedMain.mut_lagerSperre.release();
                DeadSolvedMain.mut_arbeiten.release();
            }
        }
    }
}
