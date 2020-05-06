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

                //Setze Sperre auf Storage
                RaceConditionSolved.mut_writeStorage.acquire();

                //Schreibe
                int n = RaceConditionSolved.sharedStorage + 1;
                System.out.println("Schreiber"+id+" schreibt:" + n);
                sleep((int)(Math.random()*1000));

                //Löse Sperre auf Storage
                RaceConditionSolved.mut_writeStorage.release();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}

