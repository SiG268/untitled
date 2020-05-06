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

                //Setze Sperre auf Storage
                Starvation.mut_writeStorage.acquire();

                //Schreibe
                Starvation.sharedStorage ++;
                int n = Starvation.sharedStorage;
                System.out.println("Schreiber"+id+" schreibt:" + n);
                sleep((int)(Math.random()*1000));

                //Löse Sperre auf Storage
                Starvation.mut_writeStorage.release();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}

