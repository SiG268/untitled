package LeserSchreiber.RaceConditionSolved;

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
        RaceConditionSolved.sem_Schreiber.acquire();
        Datei d = RaceConditionSolved.DS.getDatei("/root/users/user1/desktop/datei1");
        System.out.println("Schreiber"+this.id+" schreibt:"+ RaceConditionSolved.count);
        d.write(RaceConditionSolved.count);
        RaceConditionSolved.count++;
        RaceConditionSolved.sem_Schreiber.release();
    }
}
