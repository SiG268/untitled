package LeserSchreiber.StarvationSolved;

public class Leser extends Thread {

    public Leser() {
    }
    private void lesen() throws InterruptedException {
        //this.sleep(1);
        StarvationSolved.sem_Leser.acquire();
        StarvationSolved.mut_rc.acquire();
        StarvationSolved.read_count++;
        if(StarvationSolved.read_count == 1){
            StarvationSolved.sem_Schreiber.acquire();
        }
        StarvationSolved.mut_rc.release();
        Datei d = StarvationSolved.DS.getDatei("/root/users/user1/desktop/datei1");
        System.out.println("Leser liest: "+d.read());
        //this.sleep(1000);
        StarvationSolved.mut_rc.acquire();
        StarvationSolved.read_count--;
        if(StarvationSolved.read_count == 0){
            StarvationSolved.sem_Schreiber.release();
        }
        StarvationSolved.mut_rc.release();
        StarvationSolved.sem_Leser.release();
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
