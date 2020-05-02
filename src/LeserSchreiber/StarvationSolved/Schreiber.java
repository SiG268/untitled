package LeserSchreiber.StarvationSolved;

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

        StarvationSolved.sem_Schreiber.acquire();
        StarvationSolved.mut_wc.acquire();
        StarvationSolved.write_counter++;
        if(StarvationSolved.write_counter == 1){
            StarvationSolved.sem_Leser.acquire();
        }
        StarvationSolved.mut_wc.release();

        Datei d = StarvationSolved.DS.getDatei("/root/users/user1/desktop/datei1");
        System.out.println("Schreiber"+this.id+" schreibt:"+ StarvationSolved.count);
        d.write(StarvationSolved.count);
        StarvationSolved.count++;
        StarvationSolved.mut_wc.acquire();
        StarvationSolved.write_counter--;
        if (StarvationSolved.write_counter == 0){
            StarvationSolved.sem_Leser.release();
        }
        StarvationSolved.mut_wc.release();
        StarvationSolved.sem_Schreiber.release();
    }
}
