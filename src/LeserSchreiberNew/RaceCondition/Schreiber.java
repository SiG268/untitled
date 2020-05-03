package LeserSchreiberNew.RaceCondition;

public class Schreiber extends Thread{
    int id;
    public Schreiber(int i) {
        this.id =i;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int n = RaceCondition.sharedStorage;
                sleep((int)(Math.random()*1000));
                n++;
                System.out.println("Schreiber"+id+" schreibt:" +n);
                RaceCondition.sharedStorage=n;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}

