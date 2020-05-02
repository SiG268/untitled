package LeserSchreiber.Starvation;

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
        Starvation.sem_Schreiber.acquire();
        Datei d = Starvation.DS.getDatei("/root/users/user1/desktop/datei1");
        System.out.println("Schreiber"+this.id+" schreibt:"+ Starvation.count);
        d.write(Starvation.count);
        Starvation.count++;
        Starvation.sem_Schreiber.release();
    }
}
