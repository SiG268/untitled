package LeserSchreiber.RaceCondition;

public class Schreiber extends Thread{
    Speicher d;

    public Schreiber(Speicher d) {
        this.d=d;
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
        this.sleep(3000);
        d.write("Write");
    }
}
