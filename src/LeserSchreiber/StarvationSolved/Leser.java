package LeserSchreiber.StarvationSolved;


public class Leser extends Thread {

    int id;
    public Leser(int i) {
        this.id=i;
    }

    @Override
    public void run() {
        while(true){
            try {
                
                StarvationSolved.mut_arbeiten.acquire();
                StarvationSolved.ss1.acquire();
                int n = StarvationSolved.sharedStorage1;
                System.out.println("Leser"+id+" liest: "+ n);
                StarvationSolved.ss2.acquire();
                StarvationSolved.sharedStorage2 = n;
                StarvationSolved.ss2.release();
                StarvationSolved.ss1.release();
                sleep((int)(Math.random()*1000));

                StarvationSolved.mut_arbeiten.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
