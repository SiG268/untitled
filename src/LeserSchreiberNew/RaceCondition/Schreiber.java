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

                int n = RaceCondition.sharedStorage2;
                n++;
                RaceCondition.sharedStorage1 = n;
                System.out.println("Schreiber"+id+" schreibt:" + n);
                sleep((int)(Math.random()*1000));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}

