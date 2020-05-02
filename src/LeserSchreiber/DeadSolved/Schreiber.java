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
        //this.sleep(100);

        DeadSolved.sem_Schreiber.acquire();
        DeadSolved.mut_wc.acquire();
        DeadSolved.write_counter++;
        if(DeadSolved.write_counter == 1){
            DeadSolved.sem_Leser.acquire();
        }
        DeadSolved.mut_wc.release();

        Datei d = DeadSolved.DS.getDatei("/root/users/user1/desktop/datei1");
        System.out.println("Schreiber"+this.id+" schreibt:"+ DeadSolved.count);
        d.write(DeadSolved.count);
        DeadSolved.count++;
        DeadSolved.mut_wc.acquire();
        DeadSolved.write_counter--;
        if (DeadSolved.write_counter == 0){
            DeadSolved.sem_Leser.release();
        }
        DeadSolved.mut_wc.release();
        DeadSolved.sem_Schreiber.release();
    }
}
