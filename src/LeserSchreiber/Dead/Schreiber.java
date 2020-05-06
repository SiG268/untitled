package LeserSchreiber.Dead;

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
                Dead.ss2.acquire();
                int n = Dead.sharedStorage2;
                n++;
                Dead.ss1.acquire();
                Dead.sharedStorage1 = n;
                Dead.ss1.release();
                Dead.ss2.release();
                System.out.println("Schreiber"+id+" schreibt:" + n);
                sleep((int)(Math.random()*1000));
                //Dead.mut_arbeiten.release();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}

