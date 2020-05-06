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

                StarvationSolved.mut_arbeiten.acquire();
                StarvationSolved.ss2.acquire();
                int n = StarvationSolved.sharedStorage2;
                n++;
                StarvationSolved.ss1.acquire();
                StarvationSolved.sharedStorage1 = n;
                StarvationSolved.ss1.release();
                StarvationSolved.ss2.release();
                System.out.println("Schreiber"+id+" schreibt:" + n);
                sleep((int)(Math.random()*1000));
                StarvationSolved.mut_arbeiten.release();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}

