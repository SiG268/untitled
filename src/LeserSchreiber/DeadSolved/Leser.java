package LeserSchreiber.DeadSolved;


public class Leser extends Thread {

    int id;
    public Leser(int i) {
        this.id=i;
    }

    @Override
    public void run() {
        while(true){
            try {
                DeadSolved.mut_arbeiten.acquire();
                DeadSolved.ss1.acquire();
                int n = DeadSolved.sharedStorage1;
                System.out.println("Leser"+id+" liest: "+ n);
                DeadSolved.ss2.acquire();
                DeadSolved.sharedStorage2 = n;
                DeadSolved.ss2.release();
                DeadSolved.ss1.release();
                sleep((int)(Math.random()*1000));
                DeadSolved.mut_arbeiten.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
