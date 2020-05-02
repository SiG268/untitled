package LeserSchreiber.RaceCondition;

public class Leser extends Thread {
    Speicher d;

    public Leser(Speicher d) {
        this.d=d;
    }
    private void lesen() throws InterruptedException {
        this.sleep(100);
        System.out.println(d.read());
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
