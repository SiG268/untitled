package LeserSchreiber.RaceCondition;

public class Leser extends Thread {

    public Leser() {
    }
    private void lesen() throws InterruptedException {
        this.sleep(100);
        System.out.println();
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
