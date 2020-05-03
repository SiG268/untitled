package LeserSchreiber.RaceConditionSolved;

import LeserSchreiber.RaceCondition.RaceCondition;

public class Leser extends Thread {

    public Leser() {
    }
    private void lesen() throws InterruptedException {
        //this.sleep(1000);
        RaceConditionSolved.mut_counter.acquire();
        RaceConditionSolved.read_count++;
        if(RaceConditionSolved.read_count == 1){
            RaceConditionSolved.mut_arbeiten.acquire();
        }
        RaceConditionSolved.mut_counter.release();
        Datei d = RaceConditionSolved.DS.getDatei("/root/users/user1/desktop/datei1");
        System.out.println("Leser liest: "+d.read());
        RaceConditionSolved.mut_counter.acquire();
        RaceConditionSolved.read_count--;
        if(RaceConditionSolved.read_count == 0){
            RaceConditionSolved.mut_arbeiten.release();
        }
        RaceConditionSolved.mut_counter.release();
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
