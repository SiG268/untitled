package LeserSchreiber.RaceConditionSolved;


public class Leser extends Thread {

    int id;
    public Leser(int i) {
        this.id=i;
    }

    @Override
    public void run() {
        while(true){
            try {
                //Setze Sperre auf Storage
                RaceConditionSolved.mut_readCount.acquire();
                RaceConditionSolved.readCount++;
                if(RaceConditionSolved.readCount==1) RaceConditionSolved.mut_writeStorage.acquire();
                RaceConditionSolved.mut_readCount.release();

                //Lese
                RaceConditionSolved.mut_writeStorage.acquire();
                int n = RaceConditionSolved.sharedStorage;
                System.out.println("Leser"+id+" liest: "+ n);
                sleep((int)(Math.random()*1000));

                //Löse Sperre auf Storage
                RaceConditionSolved.mut_readCount.acquire();
                RaceConditionSolved.readCount--;
                if(RaceConditionSolved.readCount==0) RaceConditionSolved.mut_writeStorage.release();
                RaceConditionSolved.mut_readCount.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
