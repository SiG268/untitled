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
                
                //Dead.mut_arbeiten.acquire();
                RaceConditionSolved.ss1.acquire();
                int n = RaceConditionSolved.sharedStorage1;
                System.out.println("Leser"+id+" liest: "+ n);
                RaceConditionSolved.ss2.acquire();
                RaceConditionSolved.sharedStorage2 = n;
                RaceConditionSolved.ss2.release();
                RaceConditionSolved.ss1.release();
                sleep((int)(Math.random()*1000));

                //Dead.mut_arbeiten.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
