package LeserSchreiber.DeadSolved;

public class Leser extends Thread {

    public Leser() {
    }
    private void lesen() throws InterruptedException {
        //this.sleep(1);
        DeadSolved.sem_Leser.acquire();
        DeadSolved.mut_rc.acquire();
        DeadSolved.read_count++;
        if(DeadSolved.read_count == 1){
            DeadSolved.sem_Schreiber.acquire();
        }
        DeadSolved.mut_rc.release();
        Datei d = DeadSolved.DS.getDatei("/root/users/user1/desktop/datei1");
        System.out.println("Leser liest: "+d.read());
        //this.sleep(1000);
        DeadSolved.mut_rc.acquire();
        DeadSolved.read_count--;
        if(DeadSolved.read_count == 0){
            DeadSolved.sem_Schreiber.release();
        }
        DeadSolved.mut_rc.release();
        DeadSolved.sem_Leser.release();
    }
    @Override
    public void run() {
        while(true){
            try {
                lesen();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
