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
                StarvationSolved.mut_writeStorage.acquire();

                //Schreibe
                int n = StarvationSolved.sharedStorage + 1;
                System.out.println("Schreiber"+id+" schreibt:" + n);
                sleep((int)(Math.random()*1000));

                //Löse Sperre auf Storage
                StarvationSolved.mut_writeStorage.release();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}

