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
                StarvationSolved.mut_writeCount.acquire();
                StarvationSolved.writeCount++;
                if(StarvationSolved.writeCount == 1) StarvationSolved.mut_read.release();
                StarvationSolved.mut_writeCount.release();
                StarvationSolved.mut_write.acquire();

                //Schreibe
                StarvationSolved.sharedStorage++;
                int n = StarvationSolved.sharedStorage;
                System.out.println("Schreiber"+id+" schreibt:" + n);
                sleep((int)(Math.random()*100));

                //Löse Sperre auf Storage
                StarvationSolved.mut_write.release();
                StarvationSolved.mut_writeCount.acquire();
                StarvationSolved.writeCount--;
                if(StarvationSolved.writeCount == 0) StarvationSolved.mut_read.release();
                StarvationSolved.mut_writeCount.release();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}

