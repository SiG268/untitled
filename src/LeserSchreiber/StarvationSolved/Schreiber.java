package LeserSchreiber.StarvationSolved;

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
                StarvationSolved.mut_write.acquire();
                StarvationSolved.mut_writeStorage.acquire();

                //Schreibe
                StarvationSolved.sharedStorage++;
                int n = StarvationSolved.sharedStorage;
                System.out.println("Schreiber"+id+" schreibt:" + n);
                sleep((int)(Math.random()*100));

                //Löse Sperre auf Storage
                StarvationSolved.mut_writeStorage.release();
                StarvationSolved.mut_write.release();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}

