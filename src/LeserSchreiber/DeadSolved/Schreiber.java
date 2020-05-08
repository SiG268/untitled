package LeserSchreiber.DeadSolved;


public class Schreiber extends Thread{
    int id;
    public Schreiber(int i) {
        this.id =i;
    }

    @Override
    public void run() {
        while (true) {
            try {
                DeadSolved.mut_queue.acquire();
                //Setze Sperre auf Storage
                DeadSolved.mut_writeStorage.acquire();
                DeadSolved.mut_queue.release();
                //Schreibe
                DeadSolved.sharedStorage ++;
                int n = DeadSolved.sharedStorage;
                System.out.println("Schreiber"+id+" schreibt:" + n);
                sleep((int)(Math.random()*1000));

                //Löse Sperre auf Storage
                DeadSolved.mut_writeStorage.release();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}

