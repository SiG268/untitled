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
                //Erhöhe den geteilten Speicher um 1
                RaceCondition.sharedStorage++;
                int n = RaceCondition.sharedStorage;
                System.out.println("Schreiber"+id+" schreibt:" + n);
                //Zeit zum Schreiben
                sleep((int)(Math.random()*1000));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}

