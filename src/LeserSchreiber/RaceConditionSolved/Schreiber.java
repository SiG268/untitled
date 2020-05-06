package LeserSchreiber.RaceConditionSolved;

public class Schreiber extends Thread{
    int id;
    public Schreiber(int i) {
        this.id =i;
    }

    @Override
    public void run() {
        while (true) {
            try {

                //Dead.mut_arbeiten.acquire();
                RaceConditionSolved.ss2.acquire();
                int n = RaceConditionSolved.sharedStorage2;
                n++;
                RaceConditionSolved.ss1.acquire();
                RaceConditionSolved.sharedStorage1 = n;
                RaceConditionSolved.ss1.release();
                RaceConditionSolved.ss2.release();
                System.out.println("Schreiber"+id+" schreibt:" + n);
                sleep((int)(Math.random()*1000));
                //Dead.mut_arbeiten.release();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}

