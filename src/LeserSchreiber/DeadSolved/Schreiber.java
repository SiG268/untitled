package LeserSchreiber.DeadSolved;

public class Schreiber extends Thread{
    private int id;

    public Schreiber(int i) {
        this.id = i;
    }

    @Override
    public void run() {
        while(true){
            try {
                schreiben();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void schreiben() throws InterruptedException {
        this.sleep(10);
        System.out.println("Schreiber"+this.id+" will schreiben");
        //DeadSolved.mut_arbeite.acquire();





        DeadSolved.sem_Leser.acquire();

        DeadSolved.sem_Schreiber.acquire();
        System.out.println("Schreiber"+id+" beginnt schreiben");
        this.sleep(100);
        Datei d = DeadSolved.DS.getDatei("/root/users/user1/desktop/datei1");
        System.out.println("Schreiber"+this.id+" schreibt:"+ DeadSolved.count);
        d.write(DeadSolved.count);
        DeadSolved.count++;
        DeadSolved.sem_Schreiber.release();
        DeadSolved.sem_Leser.release();

        //DeadSolved.mut_arbeite.release();
    }
}
