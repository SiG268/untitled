package LeserSchreiber.StarvationSolvednew;



public class Schreiber extends Thread{
    int id;
    public Schreiber(int i) {
        this.id =i;
    }

    @Override
    public void run() {
        while (true) {
            try {
                StarvationSolvedNew.mut_queue.acquire();
                //Setze Sperre auf Storage
                StarvationSolvedNew.mut_writeStorage.acquire();
                StarvationSolvedNew.mut_queue.release();
                //Schreibe
                StarvationSolvedNew.sharedStorage ++;
                int n = StarvationSolvedNew.sharedStorage;
                System.out.println("Schreiber"+id+" schreibt:" + n);
                sleep((int)(Math.random()*1000));

                //Löse Sperre auf Storage
                StarvationSolvedNew.mut_writeStorage.release();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}

