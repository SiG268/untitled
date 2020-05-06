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

                DeadSolved.mut_arbeiten.acquire();
                DeadSolved.ss2.acquire();
                int n = DeadSolved.sharedStorage2;
                n++;
                DeadSolved.ss1.acquire();
                DeadSolved.sharedStorage1 = n;
                DeadSolved.ss1.release();
                DeadSolved.ss2.release();
                System.out.println("Schreiber"+id+" schreibt:" + n);
                sleep((int)(Math.random()*1000));
                DeadSolved.mut_arbeiten.release();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}

