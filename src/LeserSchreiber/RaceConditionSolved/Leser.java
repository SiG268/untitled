package LeserSchreiber.RaceConditionSolved;

public class Leser extends Thread {

    public Leser() {
    }
    private void lesen() throws InterruptedException {
        this.sleep(100);
        RaceConditionSolved.mut_arbeiten.acquire();
        RaceConditionSolved.read_count++;
        if(RaceConditionSolved.read_count == 1){
            RaceConditionSolved.sem_Schreiber.acquire();
        }
        RaceConditionSolved.mut_arbeiten.release();
        Datei d = RaceConditionSolved.DS.getDatei("/root/users/user1/desktop/datei1");
        System.out.println("Leser liest: "+d.read());
        RaceConditionSolved.mut_arbeiten.acquire();
        if(RaceConditionSolved.read_count == 0){
            RaceConditionSolved.sem_Schreiber.release();
        }
        RaceConditionSolved.mut_arbeiten.release();
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
