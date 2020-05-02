package LeserSchreiber.Dead;

public class Leser extends Thread {

    public Leser() {
    }
    private void lesen() throws InterruptedException {
        //this.sleep(1);
        Dead.sem_Leser.acquire();
        Dead.mut_rc.acquire();
        Dead.read_count++;
        if(Dead.read_count == 1){
            Dead.sem_Schreiber.acquire();
        }
        Dead.mut_rc.release();
        Datei d = Dead.DS.getDatei("/root/users/user1/desktop/datei1");
        System.out.println("Leser liest: "+d.read());
        //this.sleep(1000);
        Dead.mut_rc.acquire();
        Dead.read_count--;
        if(Dead.read_count == 0){
            Dead.sem_Schreiber.release();
        }
        Dead.mut_rc.release();
        Dead.sem_Leser.release();
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
