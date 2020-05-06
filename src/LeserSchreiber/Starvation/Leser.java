package LeserSchreiber.Starvation;


public class Leser extends Thread {

    int id;
    public Leser(int i) {
        this.id=i;
    }

    @Override
    public void run() {
        while(true){
            try {
                System.out.println("Leser"+id+" will lesen");
                Starviation.mut_arbeiten.acquire();
                Starviation.ss1.acquire();
                int n = Starviation.sharedStorage1;
                System.out.println("Leser"+id+" liest: "+ n);
                Starviation.ss2.acquire();
                Starviation.sharedStorage2 = n;
                Starviation.ss2.release();
                Starviation.ss1.release();
                sleep((int)(Math.random()*100));
                System.out.println("Leser"+id+" hat fertig gelesen");
                Starviation.mut_arbeiten.release();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
