package LeserSchreiber.Dead;


public class Schreiber extends Thread{
    int id;
    public Schreiber(int i) {
        this.id =i;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Dead.mut_queue.acquire();
                //Setze Sperre auf Storage
                Dead.mut_writeStorage.acquire();
                Dead.mut_queue.release();
                //Schreibe
                Dead.sharedStorage ++;
                int n = Dead.sharedStorage;
                System.out.println("Schreiber"+id+" schreibt:" + n);
                sleep((int)(Math.random()*1000));

                //Löse Sperre auf Storage
                Dead.mut_writeStorage.release();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}

