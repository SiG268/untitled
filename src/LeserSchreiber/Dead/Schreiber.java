package LeserSchreiber.Dead;

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

        Dead.sem_Schreiber.acquire();
        Dead.mut_wc.acquire();
        Dead.write_counter++;
        if(Dead.write_counter == 1){
            Dead.sem_Leser.acquire();
        }
        Dead.mut_wc.release();

        Datei d = Dead.DS.getDatei("/root/users/user1/desktop/datei1");
        System.out.println("Schreiber"+this.id+" schreibt:"+ Dead.count);
        d.write(Dead.count);
        Dead.count++;
        Dead.mut_wc.acquire();
        Dead.write_counter--;
        if (Dead.write_counter == 0){
            Dead.sem_Leser.release();
        }
        Dead.mut_wc.release();
        Dead.sem_Schreiber.release();
    }
}
