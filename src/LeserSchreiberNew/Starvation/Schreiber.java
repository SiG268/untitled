package LeserSchreiberNew.Starvation;

public class Schreiber extends Thread{
    int id;
    public Schreiber(int i) {
        this.id =i;
    }

    @Override
    public void run() {
        while (true) {
            try {
                RaceCondition.mut_arbeiten.acquire();
                int n = RaceCondition.sharedStorage;
                //Berechnung durchführen
                sleep((int)(Math.random()*1000));
                n++;
                System.out.println("Schreiber"+id+" schreibt:" +n);
                RaceCondition.sharedStorage=n;
                RaceCondition.mut_arbeiten.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}

