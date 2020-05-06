package LeserSchreiber.Starvation;

public class Schreiber extends Thread{
    int id;
    public Schreiber(int i) {
        this.id =i;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Schreiber"+id+"will Schreiben");
                Starviation.mut_arbeiten.acquire();
                Starviation.ss2.acquire();
                int n = Starviation.sharedStorage2;
                n++;
                Starviation.ss1.acquire();
                Starviation.sharedStorage1 = n;
                Starviation.ss1.release();
                Starviation.ss2.release();
                System.out.println("Schreiber"+id+" schreibt:" + n);
                sleep((int)(Math.random()*100));
                System.out.println("Schreiber"+id+" hat fertig geschrieben");
                Starviation.mut_arbeiten.release();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}

