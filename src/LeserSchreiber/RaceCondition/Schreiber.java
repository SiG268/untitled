package LeserSchreiber.RaceCondition;

public class Schreiber extends Thread{
    int id;
    public Schreiber(int i) {
        this.id =i;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int n = RaceCondition.sharedStorage + 1;
                System.out.println("Schreiber"+id+" schreibt:" + n);
                sleep((int)(Math.random()*1000));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}

